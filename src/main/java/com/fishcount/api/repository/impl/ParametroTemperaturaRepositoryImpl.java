package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomParametroTemperaturaRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.common.model.entity.ParametroTemperatura;

import java.math.BigDecimal;

import static com.fishcount.api.repository.spec.ParametroTemperaturaSpec.temperaturaMaximaIsGreaterThan;
import static com.fishcount.api.repository.spec.ParametroTemperaturaSpec.temperaturaMinimaIsLesserThan;

public class ParametroTemperaturaRepositoryImpl
        extends RepositoryImpl<ParametroTemperatura, Integer>
        implements CustomParametroTemperaturaRepository {

    @Override
    public ParametroTemperatura findByTemperatura(BigDecimal temperatura) {
        return getSpecRepository()
                .findOne(temperaturaMaximaIsGreaterThan(temperatura)
                        .and(temperaturaMinimaIsLesserThan(temperatura)))
                .orElse(null);
    }
}
