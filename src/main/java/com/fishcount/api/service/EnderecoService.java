package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.EnderecoDTO;
import com.fishcount.common.model.entity.Endereco;

/**
 *
 * @author lucas
 */
public interface EnderecoService extends IAbstractService<Endereco, Integer, EnderecoDTO>{
 
    Endereco incluir(Endereco endereco);
}
