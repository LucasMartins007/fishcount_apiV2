package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.financeiro.pix.LocationPix;

/**
 *
 * @author Lucas Martins
 */
public interface CustomLocationPixRepository {

    LocationPix findByIdLocation(Integer idlocation);
}
