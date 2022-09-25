package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomPagamentoParcelaRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.PagamentoParcelaSpec;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagamentoParcelaRepositoryImpl extends RepositoryImpl<PagamentoParcela, Integer> implements CustomPagamentoParcelaRepository {

    @Override
    public List<PagamentoParcela> findAllByPessoaAndPagamentoAndStatus(Integer pessoaId, Integer pagamentoId, EnumStatusPagamento statusPagamento) {
        return getSpecRepository()
                .findAll(PagamentoParcelaSpec.byPessoa(pessoaId)
                        .and(PagamentoParcelaSpec.byPagamento(pagamentoId)
                                .and(PagamentoParcelaSpec.byStatus(statusPagamento)))
                );
    }

    @Override
    public List<PagamentoParcela> findAllByPessoaAndStatus(Integer pessoaId, EnumStatusPagamento statusPagamento) {
        return getSpecRepository()
                .findAll(
                        PagamentoParcelaSpec.byPessoa(pessoaId)
                                .and(PagamentoParcelaSpec.byStatus(statusPagamento)));
    }

    @Override
    public List<PagamentoParcela> findAllByPessoaAndPagamento(Integer pessoaId, Integer pagamentoId) {
        return getSpecRepository()
                .findAll(
                        PagamentoParcelaSpec.byPessoa(pessoaId)
                                .and(PagamentoParcelaSpec.byPagamento(pagamentoId))
                                .and(PagamentoParcelaSpec.orderByDataVencimento()));
    }

}
