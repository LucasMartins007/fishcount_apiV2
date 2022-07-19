package com.fishcount.common.model.enums;

import com.fishcount.common.model.enums.pattern.IEnum;
import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import lombok.Getter;

import javax.persistence.Converter;

/**
 *
 * @author lucas
 */
@Getter
public enum EnumUnidadePeso implements IEnum<String> {

    KILO("KG", "Quilos"),
    GRAMA("GR", "Gramas"),
    MILIGRAMA("ML", "Miligramas");

    EnumUnidadePeso(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;

    private String value;
    
    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumUnidadePeso, String> {
    }
}
