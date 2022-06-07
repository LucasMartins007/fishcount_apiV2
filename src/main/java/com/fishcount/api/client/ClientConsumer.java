package com.fishcount.api.client;

import com.fishcount.common.utils.Utils;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;

/**
 *
 * @author Lucas Martins
 */
public class ClientConsumer<T> extends BaseRequest<T> {

    private ClientConsumer(HttpMethod method) {
        super.method = method;
    }

    public ClientConsumer setUrl(String url) {
        this.url = url;
        return this;
    }

    public ClientConsumer addParam(Object param) {
        this.param = this.param + "/" + param;
        return this;
    }

    public ClientConsumer addQueryParam(String key, Object value) {
        if (Utils.isEmpty(value)) {
            return this;
        }
        final String separator = Utils.isEmpty(this.queryParam) ? "?" : "&";
        this.queryParam = this.queryParam + separator + key + "=" + value;
        return this;
    }

    public ClientConsumer addHeaders(String key, String value) {
        this.headers.add(key, value);
        return this;
    }

    public ClientConsumer setBody(T body) {
        this.body = body;
        return this;
    }

    public ClientConsumer setResponseClass(Class<T> clazz) {
        this.responseClass = clazz;
        return this;
    }

    public static ClientConsumer get() {
        return new ClientConsumer(HttpMethod.GET);
    }

    public static ClientConsumer post() {
        return new ClientConsumer(HttpMethod.POST);
    }

    public static ClientConsumer put() {
        return new ClientConsumer(HttpMethod.PUT);
    }

    public static ClientConsumer delete() {
        return new ClientConsumer(HttpMethod.DELETE);
    }

    public static ClientConsumer patch() {
        return new ClientConsumer(HttpMethod.PATCH);
    }

}
