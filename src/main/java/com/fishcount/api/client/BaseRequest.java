package com.fishcount.api.client;

import com.fishcount.api.config.rest.RestTemplateConfiguration;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author Lucas Martins
 */
public abstract class BaseRequest<T> {

    protected final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

    protected String url;

    protected HttpMethod method;

    protected T body;

    protected Class responseClass;
    
    public RequestEntity<T> getRequest() {
        if (body != null) {
            return new RequestEntity<>(body, headers, method, URI.create(this.url));
        }
        return new RequestEntity<>(headers, method, URI.create(this.url));
    }
    

}
