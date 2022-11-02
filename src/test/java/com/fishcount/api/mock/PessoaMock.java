package com.fishcount.api.mock;

import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.ListUtil;

import java.util.Collections;

public class PessoaMock {

    private static final String CPF = "000.000.000-00";
    private static final String NOME = "MOCKER";

    private static final String SENHA = "MOCKER";

    public static Pessoa criarMock(){
        final Pessoa pessoa = new Pessoa();
        pessoa.setCpf(CPF);
        pessoa.setNome(NOME);
        pessoa.setSenha(SENHA);
        pessoa.setDataInclusao(DateUtil.getDate());
        pessoa.setDataAtualizacao(DateUtil.getDate());

        final Email email = EmailMock.criarMock(pessoa);
        pessoa.setEmails(ListUtil.toList(email));

        final Telefone telefone = TelefoneMock.criarMock(pessoa);
        pessoa.setTelefones(ListUtil.toList(telefone));

        final Usuario usuario = UsuarioMock.criarMock(pessoa);
        pessoa.setUsuario(usuario);

        pessoa.setLotes(Collections.singletonList(LoteMock.criarMock(pessoa)));

        return pessoa;
    }
}
