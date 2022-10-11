package com.fishcount.common.model.enums;

import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import com.fishcount.common.model.enums.pattern.IEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Converter;

/**
 * @author lucas
 */
@Getter
@RequiredArgsConstructor
public enum EnumUnidadePeso implements IEnum<Integer> {

    KILO(1,"KG"),
    GRAMA(2,"GR"),
    ;

    private final Integer key;

    private final String value;

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumUnidadePeso, Integer> {
    }

}
