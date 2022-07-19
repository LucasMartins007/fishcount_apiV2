package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;

import java.util.List;


/**
 *
 * @author lucas
 */
public interface EmailService extends IAbstractService<Email, Integer, EmailDTO> {

    Email incluir(Integer pessoaId, Email email);

    void editar(Integer pessoaId, Email email);
    
    void inativar(Integer pessoaId, Integer idEmail);
    
    List<Email> listar(Integer pessoaId);
    
    Email findByEmail(Email email);

    void onPrepareInsert(Integer idPessoa, Email email);

    void onPrepareUpdate(Integer id, Email email);
}
