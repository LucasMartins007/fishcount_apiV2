
package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.ITelefoneController;
import com.fishcount.common.model.dto.TelefoneDTO;

import java.util.List;


public class TelefoneController implements ITelefoneController {

    @Override
    public TelefoneDTO incluir(Integer idUsuario, TelefoneDTO telefoneDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Integer idUsuario, Integer idTelefone, TelefoneDTO telefoneDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TelefoneDTO> listar(Integer idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TelefoneDTO encontrar(Integer idUsuario, Integer idTelefone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}