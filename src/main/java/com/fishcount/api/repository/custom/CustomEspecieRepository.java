package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Especie;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomEspecieRepository {

    List<Especie> findAll();
    
    Especie findByDescricao(String descricao);
}
