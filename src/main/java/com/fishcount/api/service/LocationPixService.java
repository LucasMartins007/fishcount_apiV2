package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.classes.gerencianet.response.PayloadLocationResponse;
import com.fishcount.common.model.dto.LocationPixDTO;
import com.fishcount.common.model.entity.LocationPix;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public interface LocationPixService extends IAbstractService<LocationPix, Integer, LocationPixDTO> {

    LocationPix incluir(PayloadLocationResponse location);
}
