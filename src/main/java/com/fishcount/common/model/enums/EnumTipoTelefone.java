package com.fishcount.common.model.enums;

import com.fishcount.common.model.dto.pattern.IEnum;
import com.fishcount.common.model.dto.pattern.converter.AbstractEnumConverter;

import javax.persistence.Converter;

/**
 *
 * @author lucas
 */
public enum EnumTipoTelefone implements IEnum<Integer> {

    PRINCIPAL(1, "Principal"),
    ADICIONAL(2, "Adicional");

    private final Integer key;

    private final String value;

    private EnumTipoTelefone(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoTelefone, Integer> {
    }

}
