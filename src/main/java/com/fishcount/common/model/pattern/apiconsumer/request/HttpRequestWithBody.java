package com.fishcount.common.model.pattern.apiconsumer.request;

import com.fishcount.common.model.pattern.authentication.HttpsTLSProtocol;
import com.fishcount.common.model.pattern.apiconsumer.ObjectMapper;
import com.fishcount.common.model.pattern.apiconsumer.body.MultipartBody;
import com.fishcount.common.model.pattern.apiconsumer.body.RequestBodyEntity;
import com.fishcount.common.model.pattern.apiconsumer.options.Option;
import com.fishcount.common.model.pattern.apiconsumer.options.Options;
import com.fishcount.common.model.pattern.apiconsumer.request.HttpRequest;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Lucas Martins
 */
public class HttpRequestWithBody extends HttpRequest {

    public HttpRequestWithBody(String httpMethod, String url) {
        super(httpMethod, url);
    }

    @Override
    public HttpRequestWithBody setConnectTimeout(Integer connectTimeout) {
        return (HttpRequestWithBody) super.setConnectTimeout(connectTimeout);
    }

    @Override
    public HttpRequestWithBody setFollowRedirects(boolean followRedirects) {
        return (HttpRequestWithBody) super.setFollowRedirects(followRedirects);
    }

    @Override
    public HttpRequestWithBody setReadTimeout(Integer readTimeout) {
        return (HttpRequestWithBody) super.setReadTimeout(readTimeout);
    }

    @Override
    public HttpRequestWithBody setSocketTimeout(Integer socketTimeout) {
        return (HttpRequestWithBody) super.setSocketTimeout(socketTimeout);
    }

    @Override
    public HttpRequestWithBody setEntityType(Class entityType) {
        return (HttpRequestWithBody) super.setEntityType(entityType);
    }

    @Override
    public HttpRequestWithBody setTemplates(Map<String, Object> pathParamaters) {
        return (HttpRequestWithBody) super.setTemplates(pathParamaters);
    }

    @Override
    public HttpRequestWithBody addTemplate(String name, Object value) {
        return (HttpRequestWithBody) super.addTemplate(name, value);
    }

    @Override
    public HttpRequestWithBody setQueryParamaters(Map<String, Object> queryParamaters) {
        return (HttpRequestWithBody) super.setQueryParamaters(queryParamaters);
    }

    @Override
    public HttpRequestWithBody addQueryParamater(String name, Object value) {
        return (HttpRequestWithBody) super.addQueryParamater(name, value);
    }

    @Override
    public HttpRequestWithBody setHeaders(MultivaluedMap<String, Object> headerParamaters) {
        return (HttpRequestWithBody) super.setHeaders(headerParamaters);
    }

    @Override
    public HttpRequestWithBody addHeader(String name, Object value) {
        return (HttpRequestWithBody) super.addHeader(name, value);
    }

    @Override
    public HttpRequestWithBody bearerToken(String token) {
        return (HttpRequestWithBody) super.bearerToken(token);
    }

    @Override
    public HttpRequestWithBody basicAuth(String username, String password) {
        return (HttpRequestWithBody) super.basicAuth(username, password);
    }

    @Override
    public HttpRequestWithBody addPath(Object value) {
        return (HttpRequestWithBody) super.addPath(value);
    }

    @Override
    public HttpRequestWithBody setMediaType(MediaType mediaType) {
        return (HttpRequestWithBody) super.setMediaType(mediaType);
    }

    @Override
    public HttpRequestWithBody setMethod(String method) {
        return (HttpRequestWithBody) super.setMethod(method);
    }

    @Override
    public HttpRequestWithBody setSupportedProtocols(List<HttpsTLSProtocol> supportedProtocols) {
        return (HttpRequestWithBody) super.setSupportedProtocols(supportedProtocols);
    }

    @Override
    public HttpRequestWithBody addSupportedProtocol(HttpsTLSProtocol supportedProtocol) {
        return (HttpRequestWithBody) super.addSupportedProtocol(supportedProtocol);
    }

    public MultipartBody field(String name, Object value) {
        MultipartBody body = new MultipartBody(this).field(name, (value == null) ? "" : value.toString());
        this.body = body;
        return body;
    }

    public MultipartBody fields(Map<String, Object> parameters) {
        MultipartBody body = new MultipartBody(this);
        if (parameters != null) {
            for (Map.Entry<String, Object> param : parameters.entrySet()) {
                if (param.getValue() instanceof File) {
                    body.field(param.getKey(), (File) param.getValue());
                } else {
                    body.field(param.getKey(), (param.getValue() == null) ? "" : param.getValue().toString());
                }
            }
        }
        this.body = body;
        return body;
    }

    public RequestBodyEntity body(String body) {
        RequestBodyEntity b = new RequestBodyEntity(this).body(body);
        this.body = b;
        return b;
    }

    public RequestBodyEntity body(Object body) {
        final ObjectMapper objectMapper = (ObjectMapper) Options.getOption(Option.OBJECT_MAPPER);

        if (objectMapper == null) {
            throw new RuntimeException("Serialization Impossible. Can't find an ObjectMapper implementation.");
        }

        return body(objectMapper.writeValue(body));
    }

}
