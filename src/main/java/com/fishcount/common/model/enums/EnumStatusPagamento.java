package com.fishcount.common.model.enums;

import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import com.fishcount.common.model.enums.pattern.IEnum;
import lombok.Getter;

import javax.persistence.Converter;

@Getter
public enum EnumStatusPagamento implements IEnum<Integer> {

    ANALISE(0, "Em análise"),
    ABERTO(1, "Em aberto"),
    PAGAMENTO_PARCIAL(2, "Pagamento parcial"),
    FINALIZADO(3, "Finalizado"),
    ATRASADO(4, "Em Atraso"),
    ;

    private final Integer key;

    private final String value;

    EnumStatusPagamento(Integer key, String value){
        this.key = key;
        this.value = value;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumStatusPagamento, Integer> {
    }

}
