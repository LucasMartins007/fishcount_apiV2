package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomTituloParcelaRepository;
import com.fishcount.common.model.entity.TituloParcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Lucas Martins
 */
public interface TituloParcelaRepository extends JpaRepository<TituloParcela, Integer>, JpaSpecificationExecutor<TituloParcela>, CustomTituloParcelaRepository {

}
