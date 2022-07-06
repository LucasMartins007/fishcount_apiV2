package com.fishcount.api.repository.infrastructure.spec;

import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class QRCodePixSpec {

    private static final String FIELD_LOCATION = "locationPix";

    private static final String FIELD_ID_LOCATION = "idLocation";

    public static Specification<QRCodePix> byLocationId(Integer locationId) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_LOCATION).get(FIELD_ID_LOCATION), locationId);
    }
    
   
}
