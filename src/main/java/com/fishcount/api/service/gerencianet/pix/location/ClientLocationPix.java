package com.fishcount.api.service.gerencianet.pix.location;

import com.fishcount.common.model.classes.gerencianet.response.PayloadLocationResponse;
import com.fishcount.common.model.classes.gerencianet.response.PayloadQRCodeResponse;

import java.util.Date;
import java.util.List;

public interface ClientLocationPix {

    PayloadLocationResponse criarLocation(String tipoCobranca);

    List<PayloadLocationResponse> consultarLocations(Date dataInicio, Date dataFim, boolean txIdPresente, String tipoCobranca);

    PayloadLocationResponse recuperarLocation(Integer locationId);

    PayloadQRCodeResponse gerarQRCode(Integer locationId);

    void desvincularTxIdDaLocation(Integer locationId);
}
