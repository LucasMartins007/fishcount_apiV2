package com.fishcount.common.model.pattern.client;

import java.net.URI;
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
        resolverParams();
        resolverQueryParams();
        if (body != null) {
            return new RequestEntity<>(body, headers, method, URI.create(this.url));
        }
        return new RequestEntity<>(headers, method, URI.create(this.url));
    }

    private void resolverQueryParams() {
        if (queryParam == null) {
            return;
        }
        this.url = this.url + this.queryParam;
    }

    private void resolverParams() {
        if (param == null) {
            return;
        }
        this.url = this.url + this.param;
    }

}
