package com.fishcount.api.repository.impl;

import com.fishcount.api.infrastructure.spec.PagamentoParcelaSpec;
import com.fishcount.api.repository.custom.CustomPagamentoParcelaRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PagamentoParcelaRepositoryImpl extends GenericImpl<PagamentoParcela, Integer> implements CustomPagamentoParcelaRepository {

    @Override
    public List<PagamentoParcela> findAllByUsuarioAndPagamentoAndStatus(Integer idUsuario, Integer idPagamento, EnumStatusPagamento statusPagamento) {
        return getSpecRepository()
                .findAll(PagamentoParcelaSpec.byUsuarioAndPagamentoAndStatus(idUsuario, idPagamento, statusPagamento));
    }

}
