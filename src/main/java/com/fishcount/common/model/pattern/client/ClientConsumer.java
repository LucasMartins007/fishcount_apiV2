package com.fishcount.common.model.pattern.client;

import com.fishcount.common.utils.Utils;
import org.springframework.http.HttpMethod;

/**
 *
 * @author Lucas Martins
 */
public class ClientConsumer<T> extends BaseRequest<T> {

    public ClientConsumer() {
    }

    private ClientConsumer(HttpMethod method) {
        super.method = method;
    }

    public ClientConsumer<T> setUrl(String url) {
        this.url = url;
        return this;
    }

    public ClientConsumer<T> addParam(Object param) {
        if (Utils.isEmpty(this.param)){
            this.param = "/" + param;
            return this;
        }
        this.param = this.param + "/" + param;
        return this;
    }

    public ClientConsumer<T> addQueryParam(String key, Object value) {
        if (Utils.isEmpty(value)) {
            return this;
        }
        final String separator = Utils.isEmpty(this.queryParam) ? "?" : "&";
        this.queryParam = this.queryParam + separator + key + "=" + value;
        return this;
    }

    public ClientConsumer<T> addHeaders(String key, String value) {
        this.headers.add(key, value);
        return this;
    }

    public ClientConsumer<T> setBody(T body) {
        this.body = body;
        return this;
    }

    public ClientConsumer<T> setResponseClass(Class<T> clazz) {
        this.responseClass = clazz;
        return this;
    }

    public ClientConsumer<T> get() {
        return new ClientConsumer<>(HttpMethod.GET);
    }

    public ClientConsumer<T> post() {
        return new ClientConsumer<>(HttpMethod.POST);
    }

    public ClientConsumer<T> put() {
        return new ClientConsumer<>(HttpMethod.PUT);
    }

    public ClientConsumer<T> delete() {
        return new ClientConsumer<>(HttpMethod.DELETE);
    }

    public ClientConsumer<T> patch() {
        return new ClientConsumer<>(HttpMethod.PATCH);
    }

}
