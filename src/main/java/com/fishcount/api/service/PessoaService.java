package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.PessoaDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;

public interface PessoaService extends IAbstractService<Pessoa, Integer, PessoaDTO> {

    Pessoa incluir(Pessoa pessoa);

    Pessoa encontrarPessoa(Integer id);

    void atualizar(Integer id, Pessoa pessoa);

    Pessoa encontrarPessoaByUsuario(Usuario usuario);

    Pessoa encontrarPessoaByCpf(String cpf);
}
