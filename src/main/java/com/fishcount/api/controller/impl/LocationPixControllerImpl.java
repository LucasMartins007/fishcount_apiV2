package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.LocationPixController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.LocationPixService;
import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationPixControllerImpl
        extends AbstractController<LocationPixService>
        implements LocationPixController {

    private final LocationPixService locationPixService;

    @Override
    public QRCodePixDTO encontrarPorLocation(Integer pessoaId, Integer locationId) {
        return converterEntityParaDTO(locationPixService.gerarQrCodePorLocation(pessoaId, locationId), QRCodePixDTO.class);
    }

    @Override
    public QRCodePixDTO encontrarPorParcela(Integer pessoaId, Integer pagamentoParcelaId) {
        return converterEntityParaDTO(locationPixService.gerarQrCodePorPagamentoParcela(pessoaId, pagamentoParcelaId), QRCodePixDTO.class);
    }

}
