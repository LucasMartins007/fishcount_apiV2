package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomParametroTemperaturaRepository;
import com.fishcount.common.model.entity.ParametroTemperatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParametroTemperaturaRepository
        extends JpaRepository<ParametroTemperatura, Integer>, JpaSpecificationExecutor<ParametroTemperatura>, CustomParametroTemperaturaRepository {

    @Override
    ParametroTemperatura findByTemperatura(Integer temperatura);

}
