package com.fishcount.common.model.enums;

import com.fishcount.common.model.enums.pattern.IEnum;
import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Converter;

@Getter
@RequiredArgsConstructor
public enum EnumTipoEnvioEmail implements IEnum<Integer> {

    RECUPERACAO_SENHA(1, "Solicitação de alteração de senha", true, false),

    CONFIRMACAO_NOVO_CADASTRO(2, "Novo cadastro", true, false),

    CONFIRMACAO_EMAIL(3, "Confirmação de email", true, false),

    EXCECAO_SISTEMA(4, "Exceção no sistema", true, true),
    ;

    private final Integer key;

    private final String value;

    private final boolean html;

    private final boolean isSuporte;

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoEnvioEmail, Integer> {
    }
}
