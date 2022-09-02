package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomParametroTemperaturaRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.ParametroTemperaturaSpec;
import com.fishcount.common.model.entity.ParametroTemperatura;

public class ParametroTemperaturaRepositoryImpl
        extends RepositoryImpl<ParametroTemperatura, Integer>
        implements CustomParametroTemperaturaRepository {


    @Override
    public ParametroTemperatura findByTemperatura(Integer temperatura) {
        return getSpecRepository()
                .findOne(ParametroTemperaturaSpec.byTemperatura(temperatura))
                .orElse(null);
    }
}
