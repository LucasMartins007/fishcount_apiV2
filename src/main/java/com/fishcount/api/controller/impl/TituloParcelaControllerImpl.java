
package com.fishcount.api.controller.impl;

import com.fishcount.common.model.dto.financeiro.TituloParcelaDTO;

import java.util.Collections;
import java.util.List;


public class TituloParcelaControllerImpl implements com.fishcount.api.controller.TituloParcelaController {

    @Override
    public TituloParcelaDTO adicionar(Integer idUsuario, Integer idTitulo, TituloParcelaDTO tituloParcelaDTO) {
        return null;
    }

    @Override
    public void liquidar(Integer idUsuario, Integer idTitulo, Integer idTituloParcela) {
        // @Todo implementar
    }

    @Override
    public List<TituloParcelaDTO> listar(Integer idUsuario, Integer idTitulo) {
        return Collections.emptyList();
    }

    @Override
    public TituloParcelaDTO encontrar(Integer idUsuario, Integer idTitulo, Integer idTituloParcela) {
        return null;
    }

}