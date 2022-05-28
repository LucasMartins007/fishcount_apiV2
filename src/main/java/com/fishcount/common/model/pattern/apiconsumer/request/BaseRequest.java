package com.fishcount.common.model.pattern.apiconsumer.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fishcount.common.model.pattern.apiconsumer.HttpClientHelper;
import com.fishcount.common.model.pattern.apiconsumer.ResponseConsumer;
import com.fishcount.common.model.pattern.apiconsumer.request.HttpRequest;
import java.io.InputStream;

/**
 *
 * @author Lucas Martins
 */
public abstract class BaseRequest {

    protected HttpRequest httpRequest;

    protected BaseRequest() {
        super();
    }

    public BaseRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public <T> ResponseConsumer<T> asResponseConsumer(Class<? extends T> responseClass) {
        return HttpClientHelper.request(httpRequest, (Class) responseClass);
    }

    public <T> T asObject(Class<? extends T> responseClass) {
        final ResponseConsumer request = HttpClientHelper.request(httpRequest, responseClass);
        return (T) request.getResult();
    }

    public void asVoid() {
        HttpClientHelper.request(httpRequest);
    }

    public ResponseConsumer<String> asString() {
        return HttpClientHelper.request(httpRequest, String.class);
    }

    public ResponseConsumer<JsonNode> asJson() {
        return HttpClientHelper.request(httpRequest, JsonNode.class);
    }

    public ResponseConsumer<InputStream> asInputStream() {
        return HttpClientHelper.request(httpRequest, InputStream.class);
    }

}
