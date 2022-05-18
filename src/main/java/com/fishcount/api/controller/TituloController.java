
package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.ITituloController;
import com.fishcount.common.model.dto.TituloDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
public class TituloController implements ITituloController {

    @Override
    public TituloDTO incluir(Integer idUsuario, TituloDTO tituloDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TituloDTO> listar(Integer idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void liquidar(Integer idUsuario, Integer tituloId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void calncelar(Integer idUsuario, Integer tituloId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}