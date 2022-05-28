
package com.fishcount.common.model.pattern.apiconsumer.body;

import com.fishcount.common.model.pattern.apiconsumer.request.BaseRequest;
import com.fishcount.common.model.pattern.apiconsumer.request.HttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author Lucas Martins
 */
public class RequestBodyEntity extends BaseRequest implements IBody {

    private Object body;

    public RequestBodyEntity(HttpRequest httpRequest) {
        super(httpRequest);
    }

    public RequestBodyEntity body(Object body) {
        this.body = body;
        return this;
    }

    public RequestBodyEntity body(String body) {
        this.body = body;
        return this;
    }

    public Object getBody() {
        return body;
    }

    @Override
    public HttpEntity getEntity() {
        return new StringEntity(body.toString(), "UTF-8");
    }

}
