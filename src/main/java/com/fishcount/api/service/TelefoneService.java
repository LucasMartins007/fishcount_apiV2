package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.entity.Telefone;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public interface TelefoneService extends IAbstractService<Telefone, Integer, TelefoneDTO>{
    
   public Telefone incluir(Telefone telefone);
}
