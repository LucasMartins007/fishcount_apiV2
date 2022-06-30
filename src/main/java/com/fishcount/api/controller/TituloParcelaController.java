
package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.ITituloParcelaController;
import com.fishcount.common.model.dto.financeiro.TituloParcelaDTO;

import java.util.Collections;
import java.util.List;


public class TituloParcelaController implements ITituloParcelaController {

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