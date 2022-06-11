package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.financeiro.Plano;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomPlanoRepository {
    
    List<Plano> findAllAtivos();
}
