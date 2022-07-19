package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.financeiro.PagamentoDTO;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;

import java.util.List;

/**
 * @author Lucas Martins
 */
public interface PagamentoService extends IAbstractService<Pagamento, Integer, PagamentoDTO> {

    Pagamento incluir(Pagamento pagamento, Integer idUsuario);

    List<Pagamento> listarPagamentos(Integer idUsuario);

    List<PagamentoParcela> listarParcelas(Integer idUsuario, EnumStatusPagamento statusPagamento);

    Pagamento consultarCobranca(Integer idUsuario, Integer id);
}
