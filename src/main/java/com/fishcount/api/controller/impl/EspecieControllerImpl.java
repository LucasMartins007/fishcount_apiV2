package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.EspecieService;
import com.fishcount.common.model.dto.EspecieDTO;
import com.fishcount.common.utils.ListUtil;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucas Martins
 */
@RestController
public class EspecieControllerImpl extends AbstractController<EspecieService> implements com.fishcount.api.controller.EspecieController {

    @Override
    public List<EspecieDTO> listar() {
        return getService().findAll();
    }

    @Override
    public EspecieDTO encontrarPorDescricao(String descricao) {
        return converterEntityParaDTO(getService().findByDescricao(descricao), EspecieDTO.class);
    }

    @Override
    public EspecieDTO findFist() {
        return ListUtil.first(getService().findAll());
    }

}
