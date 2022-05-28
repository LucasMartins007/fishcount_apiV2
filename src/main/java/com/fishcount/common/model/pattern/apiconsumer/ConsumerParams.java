
package com.fishcount.common.model.pattern.apiconsumer;

import com.fishcount.common.model.pattern.apiconsumer.body.RequestBodyEntity;
import com.fishcount.common.model.pattern.apiconsumer.request.HttpRequest;

import java.util.Map;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Lucas Martins
 */
public class ConsumerParams extends HttpRequest {

    public ConsumerParams(Class entity) {
        super(entity);
    }

    public ConsumerParams setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public ConsumerParams setConnectTimeout(Integer connectTimeout) {
        return (ConsumerParams) super.setConnectTimeout(connectTimeout);
    }

    @Override
    public ConsumerParams setFollowRedirects(boolean followRedirects) {
        return (ConsumerParams) super.setFollowRedirects(followRedirects);
    }

    @Override
    public ConsumerParams setReadTimeout(Integer readTimeout) {
        return (ConsumerParams) super.setReadTimeout(readTimeout);
    }

    @Override
    public ConsumerParams setEntityType(Class entityType) {
        return (ConsumerParams) super.setEntityType(entityType);
    }

    @Override
    public ConsumerParams setTemplates(Map<String, Object> pathParamaters) {
        return (ConsumerParams) super.setTemplates(pathParamaters);
    }

    @Override
    public ConsumerParams addTemplate(String key, Object value) {
        return (ConsumerParams) super.addTemplate(key, value);
    }

    @Override
    public ConsumerParams setQueryParamaters(Map<String, Object> queryParamaters) {
        return (ConsumerParams) super.setQueryParamaters(queryParamaters);
    }

    @Override
    public ConsumerParams addQueryParamater(String key, Object value) {
        return (ConsumerParams) super.addQueryParamater(key, value);
    }

    @Override
    public ConsumerParams setHeaders(MultivaluedMap<String, Object> headerParamaters) {
        return (ConsumerParams) super.setHeaders(headerParamaters);
    }

    @Override
    public ConsumerParams addHeader(String key, Object value) {
        return (ConsumerParams) super.addHeader(key, value);
    }

    @Override
    public ConsumerParams bearerToken(String token) {
        return (ConsumerParams) addHeader("Authorization", "Bearer " + token);
    }

    @Override
    public ConsumerParams addPath(Object value) {
        return (ConsumerParams) super.addPath(value);
    }

    @Override
    public ConsumerParams setMediaType(MediaType mediaType) {
        return (ConsumerParams) super.setMediaType(mediaType);
    }

    @Override
    public ConsumerParams setMethod(String method) {
        return (ConsumerParams) super.setMethod(method);
    }

    public ConsumerParams setEntityData(Object body) {
        RequestBodyEntity b = new RequestBodyEntity(this).body(body);
        this.body = b;
        return this;
    }
}
