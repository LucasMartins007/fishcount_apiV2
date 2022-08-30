package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;

public interface CustomPessoaRepository {

    Pessoa findByCpf(String cpf);

    Pessoa findByUsuario(Usuario usuario);
}
