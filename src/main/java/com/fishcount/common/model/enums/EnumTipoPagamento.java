package com.fishcount.common.model.enums;

import javax.persistence.Converter;

import com.fishcount.common.model.dto.pattern.IEnum;
import com.fishcount.common.model.dto.pattern.converter.AbstractEnumConverter;

import lombok.Getter;

@Getter
public enum EnumTipoPagamento implements IEnum<Integer> {

    PIX(1, "Pix"),
    ;

    private final Integer key;

    private final String value;

    private EnumTipoPagamento(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoPagamento, Integer> {
    }

}
