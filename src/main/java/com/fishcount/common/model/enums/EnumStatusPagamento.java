package com.fishcount.common.model.enums;

import javax.persistence.Converter;

import com.fishcount.common.model.dto.pattern.IEnum;
import com.fishcount.common.model.dto.pattern.converter.AbstractEnumConverter;

import lombok.Getter;

@Getter
public enum EnumStatusPagamento implements IEnum<Integer> {

    ABERTO(1, "Em aberto"),
    PAGAMENTO_PARCIAL(2, "Pagamento parcial"),
    FINALIZADO(3, "Finalizado"),
    ;

    private Integer key;

    private String value;

    EnumStatusPagamento(Integer key, String value){
        this.key = key;
        this.value = value;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumStatusPagamento, Integer> {
    }

}
