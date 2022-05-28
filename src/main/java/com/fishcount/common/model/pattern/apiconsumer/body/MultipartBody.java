package com.fishcount.common.model.pattern.apiconsumer.body;

import com.fishcount.common.model.pattern.apiconsumer.request.BaseRequest;
import com.fishcount.common.model.pattern.apiconsumer.request.HttpRequest;
import com.fishcount.common.utils.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;

/**
 *
 * @author Lucas Martins
 */
public class MultipartBody extends BaseRequest implements IBody {

    private final Map<String, List<Object>> parameters = new LinkedHashMap<>();

    protected IBody body;

    public MultipartBody(HttpRequest httpRequest) {
        super(httpRequest);
    }

    public IBody getBody() {
        return body;
    }

    public MultipartBody field(String name, Object value) {
        List<Object> list = parameters.get(name);
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(value);
        parameters.put(name, list);

        return this;
    }

    @Override
    public HttpEntity getEntity() {
        try {
            return new UrlEncodedFormEntity(HttpUtils.getList(parameters), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
