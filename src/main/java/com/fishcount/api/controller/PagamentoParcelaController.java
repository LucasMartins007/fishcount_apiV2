package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.IPagamentoParcelaController;
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
public class PagamentoParcelaController extends AbstractController<PagamentoParcelaService> implements IPagamentoParcelaController {

    @Override
    public List<PagamentoParcelaDTO> listarParcelas(Integer idUsuario, Integer idPagamento, EnumStatusPagamento statusPagamento) {
        return converterEntityParaDTO(getService().listarParcelas(idUsuario, idPagamento, statusPagamento), PagamentoParcelaDTO.class);
    }

    @Override
    public PagamentoParcelaDTO encontrarParcela(Integer idUsuario, Integer idPagamento, Integer idParcela) {
        return converterEntityParaDTO(getService().consultarParcela(idUsuario, idPagamento, idParcela), PagamentoParcelaDTO.class);
    }

    @Override
    public QRCodePixDTO gerarQRCodeByParcela(Integer idUsuario, Integer idParcela) {
        return converterEntityParaDTO(getService().gerarQRCodeByParcela(idUsuario, idParcela), QRCodePixDTO.class);
    }

}
