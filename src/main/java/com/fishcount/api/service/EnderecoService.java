package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.EnderecoDTO;
import com.fishcount.common.model.entity.Endereco;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
public interface EnderecoService extends IAbstractService<Endereco, Integer, EnderecoDTO>{
 
    Endereco incluir(Endereco endereco);
}
