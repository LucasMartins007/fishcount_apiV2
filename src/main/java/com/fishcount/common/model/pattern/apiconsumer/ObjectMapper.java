package com.fishcount.common.model.pattern.apiconsumer;

/**
 *
 * @author Lucas Martins
 */
public interface ObjectMapper {

    <T> T readValue(String value, Class<T> valueType);

    String writeValue(Object value);
}
