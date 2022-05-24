
package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.EspecieDTO;
import com.fishcount.common.model.entity.Especie;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public interface EspecieService extends IAbstractService<Especie, Integer, EspecieDTO> {
    
    Especie incluir(Especie especie);
    
    List<Especie> listarTodos();
    
    void onPrepareInsert(Especie especie);
}