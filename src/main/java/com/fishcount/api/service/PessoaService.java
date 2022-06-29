package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.PessoaDTO;
import com.fishcount.common.model.entity.Pessoa;
import org.springframework.stereotype.Service;

@Service
public interface PessoaService extends IAbstractService<Pessoa, Integer, PessoaDTO> {

    Pessoa incluir(Pessoa pessoa);
}
