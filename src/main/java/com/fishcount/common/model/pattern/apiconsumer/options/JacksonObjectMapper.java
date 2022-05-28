package com.fishcount.common.model.pattern.apiconsumer.options;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fishcount.common.exception.FcRuntimeException;

import java.io.IOException;

/**
 *
 * @author Lucas Martins
 */
public class JacksonObjectMapper implements ObjectMapper {

    private final com.fasterxml.jackson.databind.ObjectMapper om;

    public JacksonObjectMapper() {
        this(new com.fasterxml.jackson.databind.ObjectMapper());
//        om.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public JacksonObjectMapper(com.fasterxml.jackson.databind.ObjectMapper om) {
        this.om = om;
    }

    @Override
    public <T> T readValue(String value, Class<T> valueType) {
        try {
            return om.readValue(value, valueType);
        } catch (IOException e) {
            throw new FcRuntimeException(e);
        }
    }

    @Override
    public String writeValue(Object value) {
        try {
            return om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new FcRuntimeException(e);
        }
    }
}
