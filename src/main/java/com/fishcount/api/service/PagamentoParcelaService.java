package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import com.fishcount.common.model.enums.EnumStatusPagamento;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface PagamentoParcelaService extends IAbstractService<PagamentoParcela, Integer, PagamentoParcelaDTO> {

    List<PagamentoParcela> incluirParcelas(Pagamento pagamento);
    
    List<PagamentoParcela> listarParcelas(Integer pessoaId, Integer pagamentoId, EnumStatusPagamento statusPagamento);
    
    PagamentoParcela consultarParcela(Integer pessoaId, Integer pagamentoId, Integer parcelaId);
    
    QRCodePix gerarQRCodeByParcela(Integer pessoaId, Integer pagamentoId);

}
