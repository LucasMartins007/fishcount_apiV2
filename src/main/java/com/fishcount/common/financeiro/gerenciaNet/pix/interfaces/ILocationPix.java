package com.fishcount.common.financeiro.gerenciaNet.pix.interfaces;

import com.fishcount.common.model.classes.gerencianet.PayloadLocation;
import com.fishcount.common.model.classes.gerencianet.PayloadQRCode;

import java.util.Date;
import java.util.List;

public interface ILocationPix {

    PayloadLocation criarLocation(String tipoCobranca);

    List<PayloadLocation> consultarLocations(Date dataInicio, Date dataFim, boolean txIdPresente, String tipoCobranca);

    PayloadLocation recuperarLocation(Integer locationId);

    PayloadQRCode gerarQRCode(Integer locationId);

    void desvincularTxIdDaLocation(Integer LocationId);


}
