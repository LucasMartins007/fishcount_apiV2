package com.fishcount.common.model.enums;

import com.fishcount.common.model.enums.pattern.IEnum;
import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import lombok.Getter;

import javax.persistence.Converter;

@Getter
public enum EnumTipoEnvioEmail implements IEnum<Integer> {

    RECUPERACAO_SENHA(1, "Solicitação de alteração de senha", true),

    CONFIRMACAO_NOVO_CADASTRO(2, "Novo cadastro", true),

    CONFIRMACAO_EMAIL(3, "Confirmação de email", true),

    EXCECAO_SISTEMA(4, "Exceção no sistema", true),
    ;

    private final Integer key;

    private final String value;

    private final boolean html;

    EnumTipoEnvioEmail(Integer key, String value, boolean html) {
        this.key = key;
        this.value = value;
        this.html = html;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoEnvioEmail, Integer> {
    }
}
