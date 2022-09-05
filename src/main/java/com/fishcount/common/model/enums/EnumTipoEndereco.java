package com.fishcount.common.model.enums;

import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import com.fishcount.common.model.enums.pattern.IEnum;

import javax.persistence.Converter;

/**
 *
 * @author lucas
 */
public enum EnumTipoEndereco implements IEnum<Integer> {

    PRINCIPAL(1, "Principal"),
    ADICIONAL(2, "Adicional"),
    COMERCIAL(3, "Comercial"),;

    private final Integer key;

    private final String value;

    private EnumTipoEndereco(Integer key, String value) {
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
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoEndereco, Integer> {
    }
}
