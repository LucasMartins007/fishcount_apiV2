package com.fishcount.api.service.gerencianet.pix.location.impl;

import com.fishcount.api.service.gerencianet.pix.location.LocationPix;
import com.fishcount.common.model.classes.gerencianet.PayloadLocation;
import com.fishcount.common.model.classes.gerencianet.PayloadQRCode;

import java.util.Date;
import java.util.List;

public class LocationPixImpl implements LocationPix {

    @Override
    public PayloadLocation criarLocation(String tipoCobranca) {
        return null;
    }

    @Override
    public List<PayloadLocation> consultarLocations(Date dataInicio, Date dataFim, boolean txIdPresente, String tipoCobranca) {
        return null;
    }

    @Override
    public PayloadLocation recuperarLocation(Integer locationId) {
        return null;
    }

    @Override
    public PayloadQRCode gerarQRCode(Integer locationId) {
        return null;
    }

    @Override
    public void desvincularTxIdDaLocation(Integer LocationId) {

    }
}
