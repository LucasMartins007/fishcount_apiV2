package com.fishcount.common.model.dto.pattern.converter;

import com.fishcount.common.model.dto.pattern.IEnum;

import javax.persistence.AttributeConverter;
import java.lang.reflect.ParameterizedType;


/**
 *
 * @author Lucas Martins
 * @param <T> Enum to be converted
 * @param <E> Key variable type
 */
public class AbstractEnumConverter<T extends Enum<T> & IEnum<E>, E> implements AttributeConverter<T, E> {

    private final Class<T> clazz;

    public AbstractEnumConverter() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public E convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.getKey() : null;
    }

    @Override
    public T convertToEntityAttribute(E dbData) {
        T[] enums = clazz.getEnumConstants();

        for (T e : enums) {
            if (e.getKey().equals(dbData)) {
                return e;
            }
        }

        return null;
    }
}

