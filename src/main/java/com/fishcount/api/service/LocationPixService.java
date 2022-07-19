package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.classes.gerencianet.response.PayloadLocationResponse;
import com.fishcount.common.model.dto.financeiro.pix.LocationPixDTO;
import com.fishcount.common.model.entity.financeiro.pix.LocationPix;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;

/**
 *
 * @author Lucas Martins
 */
public interface LocationPixService extends IAbstractService<LocationPix, Integer, LocationPixDTO> {

    LocationPix incluir(PayloadLocationResponse location);

    QRCodePix gerarQrCode(Integer idPessoa, Integer idLocation);
}
