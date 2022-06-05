package com.fishcount.api.client;

import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author Lucas Martins
 */
public abstract class BaseRequest<T> {

    protected final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();;

    protected String url;

    protected HttpMethod method;

    protected T body;
    
    protected Class responseClass;

}
