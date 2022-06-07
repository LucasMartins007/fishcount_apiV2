package com.fishcount.common.exception.enums;

import com.fishcount.common.exception.IFcException;

/**
 *
 * @author lucas
 */
public enum EnumFcInfraException implements IFcException {

    REPOSITORY_NOT_FOUND("{0} não encontrado para entidade {1}"),
    NULL_POINTER_EXCEPTION("Ocorreu um erro de execução por acessar referência à uma variável nula."),
    ENTITY_NOT_FOUND("Não foi possível localizar nenhum registro de {0} com o ID {1}."),
    ENUM_NOT_FOUND("Enumerador não encontrado: {0}"),

    TOKEN_NAO_INFORMADO("Token não informado ou informado incorretamente."),
    TOKEN_INVALIDO("Token inválido."),
    METODO_NAO_SUPORTADO_OU_INVALIDO("Método não suportado ou inválido para requisições Client"),
    ;

    private final String message;

    EnumFcInfraException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
