
package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.ITituloParcelaController;
import com.fishcount.common.model.dto.TituloParcelaDTO;

import java.util.List;


public class TituloParcelaController implements ITituloParcelaController {

    @Override
    public TituloParcelaDTO adicionar(Integer idUsuario, Integer idTitulo, TituloParcelaDTO tituloParcelaDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void liquidar(Integer idUsuario, Integer idTitulo, Integer idTituloParcela) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TituloParcelaDTO> listar(Integer idUsuario, Integer idTitulo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TituloParcelaDTO encontrar(Integer idUsuario, Integer idTitulo, Integer idTituloParcela) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}