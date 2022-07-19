package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.IEspecieController;
import com.fishcount.api.service.EspecieService;
import com.fishcount.common.model.dto.EspecieDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@RestController
public class EspecieController extends AbstractController<EspecieService> implements IEspecieController {

    @Override
    public List<EspecieDTO> listar() {
        return getService().findAll();
    }

    @Override
    public EspecieDTO encontrarPorDescricao(String descricao) {
        return converterEntityParaDTO(getService().findByDescricao(descricao), EspecieDTO.class);
    }

}
