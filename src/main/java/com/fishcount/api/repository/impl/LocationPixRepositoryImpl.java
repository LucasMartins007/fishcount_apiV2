package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomLocationPixRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.LocationPixSpec;
import com.fishcount.common.model.entity.financeiro.pix.LocationPix;
import org.springframework.stereotype.Repository;

@Repository
public class LocationPixRepositoryImpl extends RepositoryImpl<LocationPix, Integer> implements CustomLocationPixRepository {

    @Override
    public LocationPix findByIdLocation(Integer idlocation) {
        return getSpecRepository()
                .findOne(LocationPixSpec.locationPixByIdLocation(idlocation))
                .orElse(null);
    }

}
