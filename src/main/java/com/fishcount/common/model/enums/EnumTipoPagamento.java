package com.fishcount.common.model.enums;

import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import com.fishcount.common.model.enums.pattern.IEnum;
import lombok.Getter;

import javax.persistence.Converter;

@Getter
public enum EnumTipoPagamento implements IEnum<Integer> {

    PIX(1, "Pix"),
    ;

    private final Integer key;

    private final String value;

    EnumTipoPagamento(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoPagamento, Integer> {
    }

}
