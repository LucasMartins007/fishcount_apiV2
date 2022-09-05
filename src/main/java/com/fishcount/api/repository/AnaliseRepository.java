package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomAnaliseRepository;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author lucas
 */
@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Integer>, JpaSpecificationExecutor<Analise>, CustomAnaliseRepository {

    @Override
    List<Analise> findAllByTanqueAndStatus(Tanque tanque, EnumStatusAnalise statusAnalise);
}
