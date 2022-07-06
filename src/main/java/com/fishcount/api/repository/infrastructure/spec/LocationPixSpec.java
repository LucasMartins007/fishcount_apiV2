package com.fishcount.api.repository.infrastructure.spec;

import com.fishcount.common.model.entity.financeiro.pix.LocationPix;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LocationPixSpec {

    private static final String FIELD_LOCATION_ID = "idLocation";

    public static Specification<LocationPix> locationPixByIdLocation(Integer idLocation) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_LOCATION_ID), idLocation);
    }
}
