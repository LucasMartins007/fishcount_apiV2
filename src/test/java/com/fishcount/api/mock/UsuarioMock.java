package com.fishcount.api.mock;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;

public class UsuarioMock {

    public static Usuario criarMock(Pessoa pessoa) {
        Usuario usuario = new Usuario();

        usuario.setNome(pessoa.getNome());
        usuario.setSenha(pessoa.getSenha());

        return usuario;
    }
}
