package com.fishcount.common.model.enums;

import com.fishcount.common.model.enums.pattern.IEnum;
import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Converter;

/**
 * @author lucas
 */
@RequiredArgsConstructor
public enum EnumUnidadePeso implements IEnum<String> {

    KILO("KG", "Quilos"),
    GRAMA("GR", "Gramas"),
    MILIGRAMA("ML", "Miligramas");

    private final String key;

    private final String value;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumUnidadePeso, String> {
    }
}
