package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.Tanque;

import java.math.BigDecimal;

public interface AnaliseService extends IAbstractService<Analise, Integer, AnaliseDTO> {

    Analise requisitarInicioAnalise(Integer tanqueId);

}
