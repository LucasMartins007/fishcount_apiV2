package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomPlanoRepository;
import com.fishcount.common.model.entity.financeiro.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer>, JpaSpecificationExecutor<Plano>, CustomPlanoRepository {

    @Override
    List<Plano> findAllAtivos();
}
