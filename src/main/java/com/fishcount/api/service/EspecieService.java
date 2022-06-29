
package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.EspecieDTO;
import com.fishcount.common.model.entity.Especie;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author lucas
 */
@Service
public interface EspecieService extends IAbstractService<Especie, Integer, EspecieDTO> {
    
    Especie incluir(Especie especie);
    
    List<Especie> listarTodos();
    
    Especie findByDescricao(String descricao);
    
    void onPrepareInsert(Especie especie);
}