
package com.fishcount.common.model.pattern.apiconsumer.request;

import com.fishcount.common.model.pattern.authentication.HttpsTLSProtocol;
import com.fishcount.common.model.pattern.apiconsumer.body.IBody;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Lucas Martins
 */
public class HttpRequest extends BaseRequest {

    protected String url;

    protected IBody body;

    private Integer connectTimeout;

    private Integer socketTimeout = 2000;

    private boolean followRedirects = true;

    private Integer readTimeout;

    private Class entityType;

    private Map<String, Object> templates;

    private MultivaluedMap<String, Object> headers;

    private List<Object> path;

    private MediaType mediaType = MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8");

    private String method = HttpMethod.GET;

    private List<HttpsTLSProtocol> supportedProtocols = new ArrayList();

    private HttpRequest() {
        this.connectTimeout = 1000;
        this.readTimeout = 2000;
        this.followRedirects = true;
        this.templates = new HashMap<>();
        this.headers = new MultivaluedHashMap<>();
        this.path = new ArrayList<>();
    }

    public HttpRequest(String httpMethod, String url) {
        this();
        this.method = httpMethod;
        this.url = url;
        super.httpRequest = this;
    }

    public HttpRequest(Class entity) {
        this();
        this.entityType = entity;
        super.httpRequest = this;
    }

    public String getUrl() {
        return url;
    }

    public IBody getBody() {
        return body;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public HttpRequest setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public boolean isFollowRedirects() {
        return followRedirects;
    }

    public HttpRequest setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
        return this;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public HttpRequest setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public HttpRequest setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public Class getEntityType() {
        return entityType;
    }

    public HttpRequest setEntityType(Class entityType) {
        this.entityType = entityType;
        return this;
    }

    public Map<String, Object> getTemplates() {
        return templates;
    }

    public HttpRequest setTemplates(Map<String, Object> templates) {
        this.templates = templates;
        return this;
    }

    public HttpRequest addTemplate(String name, Object value) {
        this.templates.put(name, value);
        return this;
    }

    public HttpRequest setQueryParamaters(Map<String, Object> parameters) {
        if (parameters != null) {
            for (Map.Entry<String, Object> param : parameters.entrySet()) {
                if (param.getValue() instanceof String || param.getValue() instanceof Number || param.getValue() instanceof Boolean) {
                    addQueryParamater(param.getKey(), param.getValue());
                } else {
                    throw new RuntimeException("Parameter \"" + param.getKey() + "\" can't be sent with a GET request because of type: " + param.getValue().getClass().getName());
                }
            }
        }
        return this;
    }

    public HttpRequest addQueryParamater(String name, Object value) {
        StringBuilder queryString = new StringBuilder();
        if (this.url.contains("?")) {
            queryString.append("&");
        } else {
            queryString.append("?");
        }
        try {
            queryString.append(URLEncoder.encode(name))
                    .append("=")
                    .append(URLEncoder.encode((value == null) ? "" : value.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.url += queryString.toString();
        return this;
    }

    public MultivaluedMap<String, Object> getHeaders() {
        return headers;
    }

    public HttpRequest setHeaders(MultivaluedMap<String, Object> headerParamaters) {
        this.headers = headerParamaters;
        return this;
    }

    public HttpRequest addHeader(String name, Object value) {
        this.headers.add(name, value);
        return this;
    }

    public HttpRequest bearerToken(String token) {
        addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return this;
    }

    public HttpRequest basicAuth(String username, String password) {
        final String basic = username + ":" + password;
        addHeader(HttpHeaders.AUTHORIZATION, "Basic " + basic);
        return this;
    }

    public List<Object> getPath() {
        return path;
    }

    public HttpRequest addPath(Object value) {
        this.path.add(value);
        return this;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public HttpRequest setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public HttpRequest setMethod(String method) {
        this.method = method;
        return this;
    }

    public List<HttpsTLSProtocol> getSupportedProtocols() {
        return supportedProtocols;
    }

    public HttpRequest setSupportedProtocols(List<HttpsTLSProtocol> supportedProtocols) {
        this.supportedProtocols = supportedProtocols;
        return this;
    }

    public HttpRequest addSupportedProtocol(HttpsTLSProtocol supportedProtocol) {
        this.supportedProtocols.add(supportedProtocol);
        return this;
    }
}
