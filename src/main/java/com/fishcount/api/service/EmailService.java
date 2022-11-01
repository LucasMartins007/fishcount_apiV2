package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;

import java.util.List;


/**
 *
 * @author lucas
 */
public interface EmailService extends IAbstractService<Email, Integer, EmailDTO> {

    Email incluir(Pessoa pessoa, Email email);

    void editar(Integer id, Email email);
    
    void inativar(Integer pessoaId, Integer emailId);
    
    List<Email> listar(Integer pessoaId);
    
    Email findByEmail(Email email);

    void onPrepareUpdate(Integer id, Email email);

    void validarInsertOrUpdate(Email email);

    Email encontrarEmailPrincipal(Pessoa pessoa);

    void validarEmails(Pessoa pessoa);
}
