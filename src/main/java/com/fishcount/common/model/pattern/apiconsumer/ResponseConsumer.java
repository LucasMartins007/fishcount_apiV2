package com.fishcount.common.model.pattern.apiconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishcount.common.model.pattern.apiconsumer.options.Option;
import com.fishcount.common.model.pattern.apiconsumer.options.Options;
import com.fishcount.common.utils.HttpUtils;
import com.fishcount.common.utils.StringUtil;
import com.fishcount.common.utils.FileUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Lucas Martins
 */
public class ResponseConsumer<T> {

    private T result;

    private int statusCode;

    private String statusText;

    private boolean hasEntity;

    private MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();

    public ResponseConsumer() {
    }

    @SuppressWarnings("unchecked")
    public ResponseConsumer(HttpResponse response, Class responseClass) {
        final HttpEntity responseEntity = response.getEntity();
        final ObjectMapper objectMapper = (ObjectMapper) Options.getOption(Option.OBJECT_MAPPER);

        Header[] allHeaders = response.getAllHeaders();
        for (Header header : allHeaders) {
            String headerName = header.getName();
            List<Object> list = headers.get(headerName);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(header.getValue());
            headers.put(headerName, list);
        }

        final StatusLine statusLine = response.getStatusLine();
        this.statusCode = statusLine.getStatusCode();
        this.statusText = statusLine.getReasonPhrase();

        if (responseEntity != null) {
            String charset = "UTF-8";

            Header contentType = responseEntity.getContentType();
            if (contentType != null) {
                String responseCharset = HttpUtils.getCharsetFromContentType(contentType.getValue());
                if (StringUtil.isNotNullOrEmpty(responseCharset)) {
                    charset = responseCharset;
                }
            }

            try {
                byte[] rawBody;
                try {
                    InputStream responseInputStream = responseEntity.getContent();
                    if (HttpUtils.isGzipped(responseEntity.getContentEncoding())) {
                        responseInputStream = new GZIPInputStream(responseEntity.getContent());
                    }
                    rawBody = FileUtil.getInputStream(responseInputStream);
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }

                if (JsonNode.class.equals(responseClass)) {
                    this.result = (T) new JsonNode(new String(rawBody, charset).trim());

                } else if (String.class.equals(responseClass)) {
                    this.result = (T) new String(rawBody, charset);

                } else if (InputStream.class.equals(responseClass)) {
                    this.result = (T) new ByteArrayInputStream(rawBody);

                } else if (isXmlEntity(responseEntity)) {
                    this.result = (T) JAXBContext.newInstance(responseClass)
                            .createUnmarshaller()
                            .unmarshal(new ByteArrayInputStream(rawBody));

                } else if (objectMapper != null) {
                    this.result = (T) objectMapper.readValue(new String(rawBody, charset), responseClass);

                } else {
                    throw new Exception("Only String, JsonNode and InputStream are supported, or an ObjectMapper implementation is required.");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        EntityUtils.consumeQuietly(responseEntity);
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public boolean isSuccessful() {
        switch (statusCode / 100) {
            case 2:
                return true;
            default:
                return false;
        }
    }

    public boolean hasEntity() {
        return hasEntity;
    }

    public void setHasEntity(boolean hasEntity) {
        this.hasEntity = hasEntity;
    }

    public void setHeaders(MultivaluedMap<String, Object> headers) {
        this.headers = headers;
    }

    public String getHeaderString(String name) {
        if (headers != null) {
            return (String) headers.getFirst(name);
        }
        return null;
    }

    private boolean isXmlEntity(HttpEntity entity) {
        if (entity == null) {
            return false;
        }

        final ContentType contentType = ContentType.get(entity);

        return contentType != null
                && (ContentType.APPLICATION_XML.getMimeType().equals(contentType.getMimeType())
                || ContentType.TEXT_XML.getMimeType().equals(contentType.getMimeType()));
    }

}
