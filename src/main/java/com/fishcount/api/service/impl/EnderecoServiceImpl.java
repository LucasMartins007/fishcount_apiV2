package com.fishcount.api.service.impl;

import com.fishcount.api.service.EnderecoService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.common.model.dto.EnderecoDTO;
import com.fishcount.common.model.entity.Endereco;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class EnderecoServiceImpl extends AbstractServiceImpl<Endereco, Integer, EnderecoDTO> implements EnderecoService {

    @Override
    public Endereco incluir(Endereco endereco) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
