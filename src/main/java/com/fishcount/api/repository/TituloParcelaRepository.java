package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomTituloParcelaRepository;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.TituloParcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface TituloParcelaRepository extends JpaRepository<TituloParcela, Integer>, JpaSpecificationExecutor<TituloParcela>, CustomTituloParcelaRepository {

    @Override
    TituloParcela findByPagamentoParcela(PagamentoParcela pagamentoParcela);
}
