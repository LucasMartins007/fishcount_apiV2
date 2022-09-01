package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.AnaliseController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.AnaliseService;
import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.entity.Analise;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnaliseControllerImpl
        extends AbstractController<AnaliseService>
        implements AnaliseController {


    @Override
    public AnaliseDTO requisitarInicioAnalise(Integer tanqueId) {
        final Analise analise = getService().requisitarInicioAnalise(tanqueId);

        return converterEntityParaDTO(analise, AnaliseDTO.class);
    }

    @Override
    public List<AnaliseDTO> listarPorTanque(Integer tanqueId) {
        return null;
    }
}
