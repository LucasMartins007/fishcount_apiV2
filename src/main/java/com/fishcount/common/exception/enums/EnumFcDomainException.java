package com.fishcount.common.exception.enums;

import com.fishcount.common.exception.IFcException;

/**
 *
 * @author lucas
 */
public enum EnumFcDomainException implements IFcException {
 
    USUARIO_NAO_ENCONTRADO("Usuário com o email {0} não foi encontrado."),
    
    CAMPOS_OBRIGATORIOS("Os seguintes campos são de preenchimento obrigatório:"),
    CAMPO_MAIOR_QUE("O campo {0} deve conter um valor maior que {1}."),
    CAMPO_MENOR_QUE("O campo {0} deve conter um valor menor que {1}."),
    CAMPO_ENTRE("O campo {0} deve conter um valor entre {1} e {2}."),
    CAMPO_MAIOR_OU_IGUAL_QUE("O campo {0} deve conter um valor maior ou igual a {1}."),
    CAMPO_MENOR_OU_IGUAL_QUE("O campo {0} deve conter um valor menor ou igual a {1}."),
    CAMPO_MENOR_IGUAL_ZERO("O campo {0} deve conter um valor maior que zero."),
    CAMPO_MINIMO_CARACTERS("O campo {0} deve conter no mínimo {1} caracteres."),
    CAMPO_MAXIMO_CARACTERS("O campo {0} deve conter no máximo {1} caracteres."),
    CAMPO_IGUAL_CARACTERS("O campo {0} deve conter exatamente {1} caracteres."),
    CAMPO_SOMENTE_NUMEROS("O campo {0} permite somente números."),
    CAMPO_SOMENTE_BOOLEAN("O campo {0} permite somente valores booleanos."),
    DATA_INICIAL_MAIOR_DATA_FINAL("A data inicial {0} não pode ser maior que a data final {1}."),
    DATA_MAIOR_DATA_ATUAL("A {0} {1} não pode ser maior que a data atual."),
    DATA_MENOR_DATA_ATUAL("A {0} {1} não pode ser menor que a data atual."),
    HORA_INICIAL_MAIOR_DATA_FINAL("A hora inicial {0} não pode ser maior que a hora final {1}."),
    
    EMAIL_INVALIDO("O endereço de e-mail {0} é inválido."),
    EMAIL_DUPLICADO("O endereço de e-mail {0} já esta cadastrado no sistema, tente fazer o login."),
    
    TELEFONE_INVALIDO("O campo {0} está com o formato incorreto."),
    
    LOTE_DUPLICADO("Lote com o nome {0} já esta cadastrado no sistema."),
    
    ;
    
    private final String message;

    EnumFcDomainException(String message) {
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
