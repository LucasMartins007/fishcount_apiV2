package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomPagamentoParcelaRepository;
import com.fishcount.common.model.entity.PagamentoParcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface PagamentoParcelaRepository extends JpaRepository<PagamentoParcela, Integer>, JpaSpecificationExecutor<PagamentoParcela>, CustomPagamentoParcelaRepository {

}
