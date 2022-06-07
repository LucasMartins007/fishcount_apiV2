package com.fishcount.api.client;

import com.fishcount.api.config.rest.RestTemplateConfiguration;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import java.net.URI;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lucas Martins
 */
public class ClientConsumer<T> extends BaseRequest<T> {
    
    private ClientConsumer(HttpMethod method) {
        super.method = method;
    }

    public RequestEntity<T> doRequest() {
        if (body != null) {
            return new RequestEntity<>(body, headers, method, URI.create(this.url));
        }
        return new RequestEntity<>(headers, method, URI.create(this.url));
    }

    public ClientConsumer setUrl(String url) {
        this.url = url;
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
    
    public ClientConsumer setResponseClass(Class<T> clazz){
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
