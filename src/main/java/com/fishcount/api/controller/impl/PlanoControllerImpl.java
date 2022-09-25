package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.PlanoController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.PlanoService;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.entity.financeiro.Plano;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlanoControllerImpl
        extends AbstractController<PlanoService>
        implements PlanoController {

    private final PlanoService planoService;

    @Override
    public List<PlanoDTO> listar() {
        return converterEntityParaDTO(planoService.listarPlanos(), PlanoDTO.class);
    }

    @Override
    public PlanoDTO incluir(PlanoDTO planoDTO) {
        Plano plano = converterDTOParaEntity(planoDTO, Plano.class);

        return converterEntityParaDTO(planoService.incluir(plano), PlanoDTO.class);
    }

    @Override
    public PlanoDTO encontrar(Integer planoId) {
        return converterEntityParaDTO(planoService.findAndValidate(planoId), PlanoDTO.class);
    }

}
