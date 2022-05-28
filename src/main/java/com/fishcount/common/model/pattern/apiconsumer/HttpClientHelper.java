
package com.fishcount.common.model.pattern.apiconsumer;

import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.model.pattern.apiconsumer.options.Options;
import com.fishcount.common.model.pattern.apiconsumer.request.HttpRequest;
import com.fishcount.common.utils.HttpUtils;
import com.fishcount.common.utils.MapUtil;
import com.fishcount.common.utils.Utils;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLContext;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;

/**
 *
 * @author Lucas Martins
 */
public class HttpClientHelper {

    private static HttpClient getHttpClient(HttpRequest request) {
        RequestConfig clientConfig = RequestConfig.custom()
                .setConnectTimeout(request.getConnectTimeout())
                .setSocketTimeout(request.getSocketTimeout())
                .setConnectionRequestTimeout(request.getReadTimeout())
                .setRedirectsEnabled(request.isFollowRedirects())
                .build();

        if (!Utils.isEmpty(request.getSupportedProtocols())) {
            SSLConnectionSocketFactory contextFactory = getSSLConnectionFactory(request);

            if (contextFactory != null) {
                return HttpClientBuilder.create()
                        .setDefaultRequestConfig(clientConfig)
                        .setSSLSocketFactory(contextFactory)
                        .build();
            }
        }

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(clientConfig)
                .setSSLContext(HttpUtils.getSSLContextToIgnoreCertificate())
                .build();
    }

    public static void request(HttpRequest request) {
        request(request, null);
    }

    public static ResponseConsumer request(HttpRequest request, Class responseClass) {
        HttpRequestBase requestObj = prepareRequest(request);
        HttpClient client = getHttpClient(request);

        try {
            HttpResponse response = client.execute(requestObj);
            if (responseClass != null) {
                return new ResponseConsumer(response, responseClass);
            }
        } catch (SocketTimeoutException | ConnectTimeoutException etx){
            throw new FcRuntimeException(etx);
        } catch (Exception e) {
            throw new FcRuntimeException(e);
        } finally {
            requestObj.releaseConnection();
        }

        return null;
    }

    private static HttpRequestBase prepareRequest(HttpRequest request) {
        if (!request.getHeaders().containsKey(HttpHeaders.USER_AGENT)) {
            request.addHeader(HttpHeaders.USER_AGENT, "PostmanRuntime/7.29.0");
        }

        if (!request.getHeaders().containsKey(HttpHeaders.ACCEPT_ENCODING)) {
            request.addHeader(HttpHeaders.ACCEPT_ENCODING, Options.ACCEPT_ENCODING);
        }

        String urlToRequest = null;
        try {
            final String baseUrl = resolveTemplate(request);
            final URL url = new URL(baseUrl);
            final URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), URLDecoder.decode(url.getPath(), "UTF-8"), "", url.getRef());
            urlToRequest = uri.toURL().toString();
            if (url.getQuery() != null && !url.getQuery().trim().equals("")) {
                if (!urlToRequest.substring(urlToRequest.length() - 1).equals("?")) {
                    urlToRequest += "?";
                }
                urlToRequest += url.getQuery();
            } else if (urlToRequest.substring(urlToRequest.length() - 1).equals("?")) {
                urlToRequest = urlToRequest.substring(0, urlToRequest.length() - 1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        HttpRequestBase reqObj = null;

        switch (request.getMethod()) {
            case HttpMethod.GET:
                reqObj = new HttpGet(urlToRequest);
                break;
            case HttpMethod.POST:
                reqObj = new HttpPost(urlToRequest);
                break;
            case HttpMethod.PUT:
                reqObj = new HttpPut(urlToRequest);
                break;
            case HttpMethod.DELETE:
                reqObj = new HttpDelete(urlToRequest);
                break;
            case HttpMethod.OPTIONS:
                reqObj = new HttpOptions(urlToRequest);
                break;
            case HttpMethod.HEAD:
                reqObj = new HttpHead(urlToRequest);
                break;
        }

        Set<Map.Entry<String, List<Object>>> entrySet = request.getHeaders().entrySet();
        for (Map.Entry<String, List<Object>> entry : entrySet) {
            List<Object> values = entry.getValue();
            if (values != null) {
                for (Object value : values) {
                    reqObj.addHeader(entry.getKey(), value.toString());
                }
            }
        }

        // Set body
        if (!(request.getMethod().equals(HttpMethod.GET) || request.getMethod().equals(HttpMethod.HEAD))) {
            if (request.getBody() != null) {
                HttpEntity entity = request.getBody().getEntity();

                ((HttpEntityEnclosingRequestBase) reqObj).setEntity(entity);
            }
        }

        return reqObj;
    }

    private static String resolveTemplate(HttpRequest request) {
        String baseUrl = request.getUrl();

        if (MapUtil.isNotNullOrEmpty(request.getTemplates())) {
            Pattern pattern = Pattern.compile("\\{[^\\}]+\\}");
            Matcher matcher = pattern.matcher(baseUrl);

            while (matcher.find()) {
                final String group = matcher.group().replaceAll("\\{", "").replace("}", "");
                final Object repString = request.getTemplates().get(group);
                if (repString != null) {
                    baseUrl = matcher.replaceFirst(repString.toString());
                    matcher = pattern.matcher(baseUrl);
                }
            }
        }

        return baseUrl;
    }

    private static SSLConnectionSocketFactory getSSLConnectionFactory(HttpRequest request) {
        final SSLContext sslContext;
        final SSLConnectionSocketFactory contextFactory;

        String[] arrProtocols = new String[request.getSupportedProtocols().size()];
        for (int i = 0; i < request.getSupportedProtocols().size(); i++) {
            arrProtocols[i] = request.getSupportedProtocols().get(i).getName();
        }

        try {
            sslContext = SSLContexts.custom().build();
            contextFactory = new SSLConnectionSocketFactory(sslContext, arrProtocols, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            return null;
        }

        return contextFactory;
    }
}
