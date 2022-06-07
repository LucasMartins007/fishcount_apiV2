package com.fishcount.api.client;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author Lucas Martins
 */
public abstract class BaseRequest<T> {

    protected final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

    protected String queryParam;
    
    protected String param;

    protected String url;

    protected HttpMethod method;

    protected T body;

    protected Class responseClass;

    public RequestEntity<T> getRequest() {
        if (param != null){
            this.url = this.url + this.param;
        }
        if (queryParam != null) {
            this.url = this.url + this.queryParam;
        }
        if (body != null) {
            return new RequestEntity<>(body, headers, method, URI.create(this.url));
        }
        return new RequestEntity<>(headers, method, URI.create(this.url));
    }

}
