package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;

/**
 *
 * @author Lucas Martins
 */
public interface CustomQRCodePixRepository {
    
    QRCodePix findByLocationId(Integer locationId);

}
