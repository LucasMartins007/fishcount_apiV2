package com.fishcount.common.model.pattern.apiconsumer;

import com.fishcount.common.model.pattern.apiconsumer.request.BaseRequest;
import com.fishcount.common.model.pattern.apiconsumer.request.HttpRequestWithBody;
import com.fishcount.common.model.pattern.apiconsumer.request.HttpRequest;
import javax.ws.rs.HttpMethod;

/**
 *
 * @author Lucas Martins
 */
public class ApiConsumer extends BaseRequest {

    private String url;

    private ApiConsumer(String target) {
        if (target == null) {
            throw new IllegalArgumentException("API url may not be null");
        }
        this.url = target;
    }

    public static ApiConsumer create(String target) {
        return new ApiConsumer(target);
    }

    public ApiConsumer configure(ConsumerParams parameters) {
        parameters.setUrl(this.url);
        this.httpRequest = parameters;
        return this;
    }

    public static HttpRequest get(String url) {
        return new HttpRequest(HttpMethod.GET, url);
    }

    public static HttpRequestWithBody post(String url) {
        return new HttpRequestWithBody(HttpMethod.POST, url);
    }

    public static HttpRequestWithBody put(String url) {
        return new HttpRequestWithBody(HttpMethod.PUT, url);
    }

    public static HttpRequestWithBody delete(String url) {
        return new HttpRequestWithBody(HttpMethod.DELETE, url);
    }
}
