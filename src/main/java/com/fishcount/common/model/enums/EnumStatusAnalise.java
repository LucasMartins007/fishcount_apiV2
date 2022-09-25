package com.fishcount.common.model.enums;

import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import com.fishcount.common.model.enums.pattern.IEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Converter;

@Getter
@RequiredArgsConstructor
public enum EnumStatusAnalise implements IEnum<Integer> {

    ANALISE_NAO_REALIZADA(1, "Análise não realizada"),

    AGUARDANDO_ANALISE(2, "Aguardando análise"),

    ANALISE_CONCLUIDA(3, "Análise concluída"),

    FALHA_ANALISE(4, "Análise com falha"),
    ;

    private final Integer key;

    private final String value;

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumStatusAnalise, Integer> {
    }
}
