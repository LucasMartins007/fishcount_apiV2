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
    public List<PagamentoParcela> findAllByUsuarioAndPagamentoAndStatus(Integer pessoaId, Integer pagamentoId, EnumStatusPagamento statusPagamento) {
        return getSpecRepository()
                .findAll(PagamentoParcelaSpec.byUsuarioAndPagamentoAndStatus(pessoaId, pagamentoId, statusPagamento));
    }

    @Override
    public List<PagamentoParcela> findAllByUsuarioAndStatus(Integer pessoaId, EnumStatusPagamento statusPagamento) {
        return getSpecRepository()
                .findAll(
                        PagamentoParcelaSpec.byUsuario(pessoaId)
                                .and(PagamentoParcelaSpec.byStatus(statusPagamento)));
    }

}
