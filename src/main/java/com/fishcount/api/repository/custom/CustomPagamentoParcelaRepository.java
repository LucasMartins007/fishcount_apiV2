package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomPagamentoParcelaRepository {

    List<PagamentoParcela> findAllByPessoaAndPagamentoAndStatus(Integer pessoaId, Integer pagamentoId, EnumStatusPagamento statusPagamento);

    List<PagamentoParcela> findAllByPessoaAndStatus(Integer pessoaId, EnumStatusPagamento statusPagamento);

    List<PagamentoParcela> findAllByPessoaAndPagamento(Integer pessoaId, Integer pagamentoId);
}
