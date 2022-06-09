
package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomLocationPixRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.LocationPix;
import org.springframework.stereotype.Repository;

@Repository
public class LocationPixRepositoryImpl extends GenericImpl<LocationPix, Integer> implements CustomLocationPixRepository {

}
