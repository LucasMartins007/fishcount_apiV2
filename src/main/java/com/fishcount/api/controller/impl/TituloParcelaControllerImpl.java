
package com.fishcount.api.controller.impl;

import com.fishcount.common.model.dto.financeiro.TituloParcelaDTO;

import java.util.Collections;
import java.util.List;


public class TituloParcelaControllerImpl implements com.fishcount.api.controller.TituloParcelaController {

    @Override
    public TituloParcelaDTO adicionar(Integer usuarioId, Integer tituloId, TituloParcelaDTO tituloParcelaDTO) {
        return null;
    }

    @Override
    public void liquidar(Integer usuarioId, Integer tituloId, Integer idTituloParcela) {
        // @Todo implementar
    }

    @Override
    public List<TituloParcelaDTO> listar(Integer usuarioId, Integer tituloId) {
        return Collections.emptyList();
    }

    @Override
    public TituloParcelaDTO encontrar(Integer usuarioId, Integer tituloId, Integer tituloParcelaId) {
        return null;
    }

}