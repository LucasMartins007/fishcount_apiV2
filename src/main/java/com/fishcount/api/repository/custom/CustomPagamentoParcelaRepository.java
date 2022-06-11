package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomPagamentoParcelaRepository {

    List<PagamentoParcela> findAllByUsuarioAndPagamentoAndStatus(Integer idUsuario, Integer idPagamento, EnumStatusPagamento statusPagamento);
}
