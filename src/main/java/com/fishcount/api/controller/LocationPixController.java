package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.ILocationPixController;
import com.fishcount.api.service.LocationPixService;
import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationPixController extends AbstractController<LocationPixService> implements ILocationPixController {

    @Override
    public QRCodePixDTO gerarQRCode(Integer idUsuario, Integer locationId) {
        return converterEntityParaDTO(getService().gerarQrCode(idUsuario, locationId), QRCodePixDTO.class);
    }

}
