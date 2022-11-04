package com.fishcount.api.mock;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.utils.ListUtil;

public class UsuarioMock {

    public static Usuario criarMock(Pessoa pessoa) {
        final Usuario usuario = new Usuario();

        usuario.setNome(pessoa.getNome());
        usuario.setSenha(pessoa.getSenha());
        usuario.setEmail(ListUtil.first(pessoa.getEmails()).getDescricao());
        usuario.setAtivo(true);

        return usuario;
    }
}
