package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.ParametroTemperatura;

import java.math.BigDecimal;

public interface CustomParametroTemperaturaRepository {

    ParametroTemperatura findByTemperatura(BigDecimal temperatura);

}
