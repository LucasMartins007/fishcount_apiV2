package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.financeiro.pix.LocationPix;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class LocationPixSpec {

    private final static String FIELD_LOCATION_ID = "idLocation";

    public static Specification<LocationPix> locationPixByIdLocation(Integer idLocation) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_LOCATION_ID), idLocation);
    }
}
