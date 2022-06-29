package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Service
public interface PagamentoParcelaService extends IAbstractService<PagamentoParcela, Integer, PagamentoParcelaDTO> {

    List<PagamentoParcela> incluirParcelas(Pagamento pagamento);
    
    List<PagamentoParcela> listarParcelas(Integer idUsuario, Integer idPagamento, EnumStatusPagamento statusPagamento);
    
    PagamentoParcela consultarParcela(Integer idUsuario, Integer idPagamento, Integer idParcela);
    
    QRCodePix gerarQRCodeByParcela(Integer idUsuario, Integer idParcela);

}
