package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.TituloService;
import com.fishcount.common.model.dto.financeiro.TituloDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
public class TituloControllerImpl extends AbstractController<TituloService> implements com.fishcount.api.controller.TituloController {

    @Override
    public TituloDTO incluir(Integer idUsuario, TituloDTO tituloDTO) {
       return null;
    }

    @Override
    public List<TituloDTO> listar(Integer idUsuario) {
        return Collections.emptyList();
    }

    @Override
    public void liquidar(Integer idUsuario, Integer tituloId) {
        // @Todo implementar
    }

    @Override
    public void calcelar(Integer idUsuario, Integer tituloId) {
        // @Todo implementar
    }

}
