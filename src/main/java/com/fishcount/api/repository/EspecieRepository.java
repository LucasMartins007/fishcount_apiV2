package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomEspecieRepository;
import com.fishcount.common.model.entity.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Lucas Martins
 */
public interface EspecieRepository extends JpaRepository<Especie, Integer>, JpaSpecificationExecutor<Especie>, CustomEspecieRepository {

    @Override
    List<Especie> findAllOrderByDescricao();

    @Override
    Especie findByDescricao(String descricao);

}
