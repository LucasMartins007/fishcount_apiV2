package com.fishcount.common.model.enums;

import com.fishcount.common.model.enums.pattern.IEnum;
import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import lombok.Getter;

import javax.persistence.Converter;

@Getter
public enum EnumStatusCobranca implements IEnum<Integer> {

    ATIVA(1, "Ativa"),
    CONCLUIDA(2, "Concluida"),
    REMOVIDA_PELO_USUARIO_RECEBEDOR(3, "Removida pelo usuário recebedor"),
    REMOVIDA_PELO_PSP(4, "Removida pelo psp");

    private final Integer key;

    private final String value;

    EnumStatusCobranca(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumStatusCobranca, Integer> {
    }

}
