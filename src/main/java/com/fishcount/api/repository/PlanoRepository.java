package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomPlanoRepository;
import com.fishcount.common.model.entity.financeiro.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Lucas Martins
 */
public interface PlanoRepository extends JpaRepository<Plano, Integer>, JpaSpecificationExecutor<Plano>, CustomPlanoRepository {

}
