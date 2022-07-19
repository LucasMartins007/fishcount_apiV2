package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.PlanoService;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.entity.financeiro.Plano;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlanoControllerImpl extends AbstractController<PlanoService> implements com.fishcount.api.controller.PlanoController {

    @Override
    public List<PlanoDTO> listar() {
        return converterEntityParaDTO(getService().listarPlanos(), PlanoDTO.class);
    }

    @Override
    public PlanoDTO incluir(PlanoDTO planoDTO) {
        Plano plano = converterDTOParaEntity(planoDTO, Plano.class);

        return converterEntityParaDTO(getService().incluir(plano), PlanoDTO.class);
    }

    @Override
    public PlanoDTO encontrar(Integer planoId) {
        return converterEntityParaDTO(getService().findAndValidate(planoId), PlanoDTO.class);
    }

}
