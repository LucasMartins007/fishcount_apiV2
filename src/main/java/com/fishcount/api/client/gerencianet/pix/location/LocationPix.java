package com.fishcount.api.client.gerencianet.pix.location;

import com.fishcount.common.model.classes.gerencianet.PayloadLocation;
import com.fishcount.common.model.classes.gerencianet.PayloadQRCode;

import java.util.Date;
import java.util.List;

public interface LocationPix {

    PayloadLocation criarLocation(String tipoCobranca);

    List<PayloadLocation> consultarLocations(Date dataInicio, Date dataFim, boolean txIdPresente, String tipoCobranca);

    PayloadLocation recuperarLocation(Integer locationId);

    PayloadQRCode gerarQRCode(Integer locationId);

    void desvincularTxIdDaLocation(Integer LocationId);
}
