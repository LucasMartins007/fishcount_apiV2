package com.fishcount.common.model.enums;

import javax.persistence.Converter;

import com.fishcount.common.model.dto.pattern.IEnum;
import com.fishcount.common.model.dto.pattern.converter.AbstractEnumConverter;

import lombok.Getter;

@Getter
public enum EnumStatusCobranca implements IEnum<Integer> {

    ATIVA(1, "Ativa"),
    CONCLUIDA(2, "Concluida"),
    REMOVIDA_PELO_USUARIO_RECEBEDOR(3, "Removida pelo usuário recebedor"),
    REMOVIDA_PELO_PSP(4, "Removida pelo psp");

    private final Integer key;

    private final String value;

    private EnumStatusCobranca(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumStatusCobranca, Integer> {
    }

}
