package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.ParametroTemperatura;

public interface CustomParametroTemperaturaRepository {

    ParametroTemperatura findByTemperatura(Integer temperatura);

}
