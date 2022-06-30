package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.PessoaDTO;
import com.fishcount.common.model.entity.Pessoa;

public interface PessoaService extends IAbstractService<Pessoa, Integer, PessoaDTO> {

    Pessoa incluir(Pessoa pessoa);

    Pessoa encontrarPessoa(Integer id);
}
