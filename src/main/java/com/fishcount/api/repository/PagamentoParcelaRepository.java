package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomPagamentoParcelaRepository;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface PagamentoParcelaRepository extends JpaRepository<PagamentoParcela, Integer>, JpaSpecificationExecutor<PagamentoParcela>, CustomPagamentoParcelaRepository {

    @Override
    List<PagamentoParcela> findAllByUsuarioAndPagamentoAndStatus(Integer idUsuario, Integer idPagamento, EnumStatusPagamento statusPagamento);

    @Override
    List<PagamentoParcela> findAllByUsuarioAndStatus(Integer idUsuario, EnumStatusPagamento statusPagamento);
}
