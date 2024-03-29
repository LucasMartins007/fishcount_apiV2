package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.AnaliseController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.AnaliseService;
import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnaliseControllerImpl
        extends AbstractController<AnaliseService>
        implements AnaliseController {

    private final AnaliseService analiseService;

    @Override
    public AnaliseDTO requisitarInicioAnalise(Integer tanqueId, BigDecimal pesoAtual, EnumUnidadePeso unidadePeso, BigDecimal temperatura) {
        final Analise analise = analiseService.requisitarInicioAnalise(tanqueId, pesoAtual, unidadePeso, temperatura);

        return converterEntityParaDTO(analise, AnaliseDTO.class);
    }

    @Override
    public AnaliseDTO simularAnaliseConcluida(Integer analiseId, Integer tanqueId, Integer qtdePeixes, BigDecimal temperatura) {
        final Analise analise = analiseService.simularAnaliseConcluida(tanqueId, analiseId, qtdePeixes, temperatura);

        return converterEntityParaDTO(analise, AnaliseDTO.class);
    }

    @Override
    public List<AnaliseDTO> listarPorTanque(Integer tanqueId, EnumStatusAnalise statusAnalise) {
        final List<Analise> analises =  analiseService.listarPorTanque(tanqueId, statusAnalise);

        return converterEntityParaDTO(analises, AnaliseDTO.class);
    }
}
