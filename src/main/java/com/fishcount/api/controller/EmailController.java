package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.IEmailController;
import com.fishcount.common.model.dto.EmailDTO;

import java.util.List;

public class EmailController implements IEmailController {

    @Override
    public EmailDTO incluir(Integer idUsuario, EmailDTO emailDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Integer idUsuario, Integer idEmail, EmailDTO emailDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmailDTO> listar(Integer idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmailDTO encontrar(Integer idUsuario, Integer idEmail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
