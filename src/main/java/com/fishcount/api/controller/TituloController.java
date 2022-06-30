package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.ITituloController;
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
public class TituloController extends AbstractController<TituloService> implements ITituloController {

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
