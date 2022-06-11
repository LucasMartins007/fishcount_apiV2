package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class QRCodePixSpec {

    private final static String FIELD_LOCATION = "locationPix";
    
    private final static String FIELD_ID_LOCATION = "idLocation";

    public static Specification<QRCodePix> byLocationID(Integer locationId) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_LOCATION).get(FIELD_ID_LOCATION), locationId);
    }
}
