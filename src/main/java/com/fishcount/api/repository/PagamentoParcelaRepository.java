package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomPagamentoParcelaRepository;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface PagamentoParcelaRepository extends JpaRepository<PagamentoParcela, Integer>, JpaSpecificationExecutor<PagamentoParcela>, CustomPagamentoParcelaRepository {

    @Override
    List<PagamentoParcela> findAllByUsuarioAndPagamentoAndStatus(Integer idUsuario, Integer idPagamento, EnumStatusPagamento statusPagamento);
}
