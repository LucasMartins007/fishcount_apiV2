package com.fishcount.api.client;

import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

/**
 *
 * @author Lucas Martins
 */
public class ClientConsumer<T> extends BaseRequest<T> {
    
    private ClientConsumer(String url) {
        super.url = url;
    }

    private ClientConsumer(String key, String value) {
        super.headers.add(key, value);
    }

    private ClientConsumer(HttpMethod method) {
        super.method = method;
    }

    private ClientConsumer(T body) {
        super.body = body;
    }

    public RequestEntity<T> doRequest() {
        if (body != null) {
            return new RequestEntity<>(body, headers, method, URI.create(this.url));
        }
        return new RequestEntity<>(headers, method, URI.create(this.url));
    }

    public ClientConsumer setUrl(String url) {
        return new ClientConsumer(url);
    }

    public ClientConsumer addHeaders(String key, String value) {
        return new ClientConsumer(key, key);
    }

    public ClientConsumer setBody(T body) {
        return new ClientConsumer(body);
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
