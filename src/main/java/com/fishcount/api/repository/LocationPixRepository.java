package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomLocationPixRepository;
import com.fishcount.common.model.entity.LocationPix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface LocationPixRepository extends JpaRepository<LocationPix, Integer>, JpaSpecificationExecutor<LocationPix>, CustomLocationPixRepository {

}
