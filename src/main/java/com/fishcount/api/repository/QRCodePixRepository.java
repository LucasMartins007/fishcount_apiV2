package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomQRCodePixRepository;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface QRCodePixRepository extends JpaRepository<QRCodePix, Integer>, JpaSpecificationExecutor<QRCodePix>, CustomQRCodePixRepository {

    @Override
    QRCodePix findByLocationId(Integer locationId);
}
