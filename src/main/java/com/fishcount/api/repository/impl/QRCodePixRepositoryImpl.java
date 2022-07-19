package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomQRCodePixRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.QRCodePixSpec;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import org.springframework.stereotype.Repository;

@Repository
public class QRCodePixRepositoryImpl extends RepositoryImpl<QRCodePix, Integer> implements CustomQRCodePixRepository {

    @Override
    public QRCodePix findByLocationId(Integer locationId) {
        return getSpecRepository()
                .findOne(QRCodePixSpec.byLocationId(locationId))
                .orElse(null);
    }

}
