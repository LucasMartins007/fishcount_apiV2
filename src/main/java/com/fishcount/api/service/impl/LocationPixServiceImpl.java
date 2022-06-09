
package com.fishcount.api.service.impl;

import com.fishcount.api.service.LocationPixService;
import com.fishcount.common.model.classes.gerencianet.response.PayloadLocationResponse;
import com.fishcount.common.model.dto.LocationPixDTO;
import com.fishcount.common.model.entity.LocationPix;
import org.springframework.stereotype.Service;

@Service
public class LocationPixServiceImpl extends AbstractServiceImpl<LocationPix, Integer, LocationPixDTO> implements LocationPixService {

    @Override
    public LocationPix incluir(PayloadLocationResponse payload) {
        final LocationPix location = new LocationPix();
        
        location.setCriacao(payload.getCriacao());
        location.setLocation(payload.getLocation());
        location.setTipoCob(payload.getTipoCob());
        location.setIdLocation(payload.getId());
        
        return getRepository().save(location);
    }

}
