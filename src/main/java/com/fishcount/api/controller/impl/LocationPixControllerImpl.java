package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.LocationPixService;
import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationPixControllerImpl extends AbstractController<LocationPixService> implements com.fishcount.api.controller.LocationPixController {

    @Override
    public QRCodePixDTO gerarQRCode(Integer idPessoa, Integer locationId) {
        return converterEntityParaDTO(getService().gerarQrCode(idPessoa, locationId), QRCodePixDTO.class);
    }

}
