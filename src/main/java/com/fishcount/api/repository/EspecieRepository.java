package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomEspecieRepository;
import com.fishcount.common.model.entity.Especie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Lucas Martins
 */
public interface EspecieRepository extends JpaRepository<Especie, Integer>, JpaSpecificationExecutor<Especie>, CustomEspecieRepository {

    @Override
    List<Especie> findAll();

    @Override
    Especie findByDescricao(String descricao);

}
