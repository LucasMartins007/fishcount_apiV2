package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.PagamentoParcelaService;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@RestController
public class PagamentoParcelaControllerImpl extends AbstractController<PagamentoParcelaService> implements com.fishcount.api.controller.PagamentoParcelaController {

    @Override
    public List<PagamentoParcelaDTO> listar(Integer pessoaId, Integer pagamentoId, EnumStatusPagamento statusPagamento) {
        return converterEntityParaDTO(getService().listarParcelas(pessoaId, pagamentoId, statusPagamento), PagamentoParcelaDTO.class);
    }

    @Override
    public PagamentoParcelaDTO encontrar(Integer pessoaId, Integer pagamentoId, Integer parcelaId) {
        return converterEntityParaDTO(getService().consultarParcela(pessoaId, pagamentoId, parcelaId), PagamentoParcelaDTO.class);
    }

    @Override
    public QRCodePixDTO gerarQRCode(Integer pessoaId, Integer pagamentoId, Integer parcelaId) {
        return converterEntityParaDTO(getService().gerarQRCodeByParcela(pessoaId, parcelaId), QRCodePixDTO.class);
    }

}
