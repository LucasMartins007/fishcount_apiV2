package com.fishcount.common.model.enums;

import com.fishcount.common.model.enums.pattern.IEnum;
import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Converter;

/**
 * @author lucas
 */
@Getter
@RequiredArgsConstructor
public enum EnumUnidadePeso implements IEnum<String> {

    KILO("KG", "Quilos"),
    GRAMA("GR", "Gramas"),
    MILIGRAMA("ML", "Miligramas");

    private final String key;

    private final String value;

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumUnidadePeso, String> {
    }
}
