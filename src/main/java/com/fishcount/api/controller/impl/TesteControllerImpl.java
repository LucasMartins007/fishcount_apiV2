package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.AnaliseController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.AnaliseService;
import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.entity.Tanque;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TesteControllerImpl extends AbstractController<AnaliseService> implements AnaliseController {

    @Override
    public BigDecimal teste(TanqueDTO tanqueDTO, Integer especieId) {
        final Tanque tanque = converterDTOParaEntity(tanqueDTO, Tanque.class);

        return getService().realizarCalculoInicial(tanque, especieId);
    }
}
