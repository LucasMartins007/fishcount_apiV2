package com.fishcount.api.mock;

import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.enums.EnumTipoEmail;
import com.fishcount.common.utils.DateUtil;

public class EmailMock {

    private static final String EMAIL = "teste@mock.com";

    public static Email criarMock(Pessoa pessoa){
        final Email email = new Email();

        email.setTipoEmail(EnumTipoEmail.PRINCIPAL);
        email.setAtivo(true);
        email.setDataInclusao(DateUtil.getDate());
        email.setDataAtualizacao(DateUtil.getDate());
        email.setDescricao(EMAIL);
        email.setPessoa(pessoa);
        return email;
    }
}
