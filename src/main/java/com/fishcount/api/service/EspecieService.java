
package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.EspecieDTO;
import com.fishcount.common.model.entity.Especie;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public interface EspecieService extends IAbstractService<Especie, Integer, EspecieDTO> {
    
    Especie incluir(Especie especie);
}