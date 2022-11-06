package com.fishcount.common.exception.enums;

import com.fishcount.common.exception.IFcException;

/**
 *
 * @author lucas
 */
public enum EnumFcDomainException implements IFcException {
 
    USUARIO_NAO_ENCONTRADO("Usuário com o email {0} não foi encontrado."),
    CREDENCIAIS_INVALIDAS("Email ou senha incorretos, por favor, tente novamente."),

    CPF_DUPLICADO("Cpf {0} já esta cadastrado no sistema, verifique o seu login e tente novamente."),

    CPF_INVALIDO("O cpf {0} é inválido, tente novamente"),
    
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
    EMAIL_PRINCIPAL_NAO_INFORMADO("Email principal Não informado"),
    EMAIL_DUPLICADO("O endereço de e-mail {0} já esta cadastrado no sistema, tente fazer o login."),
    EMAIL_PRINCIPAL_NAO_PODE_SER_INATIVADO("O email {0} é o seu email de login, você não pode inativa-lo."),
    EMAIL_PRINCIPAL_DUPLICADO("Você não pode ter mais de um email principal, esse é o seu email de login. "),
    
    TELEFONE_INVALIDO("O campo {0} está com o formato incorreto."),
    TELEFONE_DUPLICADO("o telefone {0} já está cadastrado no sistema."),
    TELEFONE_PRINCIPAL_DUPLICADO("Você não pode ter mais de um telefone principal."),
    TELEFONE_PRINCIPAL_NAO_PODE_SER_INATIVADO("Você não pode excluir o seu telefone principal."),
    
    
    LOTE_DUPLICADO("Lote com o nome {0} já esta cadastrado no sistema."),

    LOTE_NAO_PERTENCE_PESSOA("Lote {0} não pertence a pessoa {0}"),

    USUARIO_POSSUI_TITULO_ABERTO("O usuario {0} possui pendências no sistema, não é possível criar novo contrato"),

    USUARIO_POSSUI_PAGAMENTOS_EM_ABERTO_OU_EM_ANALISE("Usuário possui pendências em aberto ou em análise, por favor, conclua-os antes de gerar novos."),

    CONFIGURACAO_TEMPERATURA_NAO_VALIDADA("A configuração de cálculo para a temperatura {0} não existe."),

    TANQUE_NAO_ENCONTRADO("O tanque {0} não existe ou não é do usuário logado, por favor, tente novamente!"),
    PESSOA_NAO_ENCONTRADA_POR_CPF("O cpf {0} não pertence a nenhuma pessoa cadastrada no sistema. "),
    ANALISE_NAO_INICIADA("A analise {0} já esta concluída ou não foi ainda preparada para receber os dados do sonar."),

    ANALISE_AGUARDANDO_JA_EXISTE("O tanque {0} já está esperando uma análise do sonar, finalize a análise requisitada, para iniciar outra."),

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
