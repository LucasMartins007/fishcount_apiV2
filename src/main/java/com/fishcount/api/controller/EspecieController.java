package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.IEspecieController;
import com.fishcount.api.service.EspecieService;
import com.fishcount.common.model.dto.EspecieDTO;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas Martins
 */
@RestController
public class EspecieController extends AbstractController<EspecieService> implements IEspecieController {

    @Override
    public List<EspecieDTO> listarEspecies() {
        return getService().findAll();
    }

    @Override
    public EspecieDTO findByDescricao(String descricao) {
        return converterEntityParaDTO(getService().findByDescricao(descricao), EspecieDTO.class);
    }

}
