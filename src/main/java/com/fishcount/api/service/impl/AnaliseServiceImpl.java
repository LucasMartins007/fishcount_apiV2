package com.fishcount.api.service.impl;

import com.fishcount.api.service.AnaliseService;
import com.fishcount.api.service.EspecieService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.Tanque;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AnaliseServiceImpl
        extends AbstractServiceImpl<Analise, Integer, AnaliseDTO>
        implements AnaliseService {


    @Override
    public BigDecimal realizarCalculoInicial(Tanque tanque, Integer especieId) {
        Especie especie = getService(EspecieService.class).findAndValidate(especieId);



        return BigDecimal.TEN;
    }
}
