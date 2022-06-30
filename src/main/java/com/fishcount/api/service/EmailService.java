package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * @author lucas
 */
public interface EmailService extends IAbstractService<Email, Integer, EmailDTO> {

    Email incluir(Integer idUsuario, Email email);

    void editar(Integer idUsuario, Email email);
    
    void inativar(Integer idUsuario, Integer idEmail);
    
    List<Email> listar(Integer idUsuario);
    
    Email findByEmail(Email email);

}
