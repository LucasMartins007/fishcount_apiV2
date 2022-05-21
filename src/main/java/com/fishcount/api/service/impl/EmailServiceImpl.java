package com.fishcount.api.service.impl;

import com.fishcount.api.repository.EmailRepository;
import com.fishcount.api.service.EmailService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.validators.EmailValidator;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.utils.DateUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl extends AbstractServiceImpl<Email, Integer, EmailDTO> implements EmailService {

    private final EmailValidator emailValidator = new EmailValidator();
    
    @Override
    public Email findByEmail(Email email) {
        return getRepository(EmailRepository.class).findAtivoByDescricao(email.getDescricao());
    }

    @Override
    public Email incluir(Integer idUsuario, Email email) {
        onPrepareInsert(idUsuario, email);
        
        emailValidator.validateInsertOrUpdate(email);
        
        return getRepository().save(email);
    }
    
    @Override
    public void editar(Integer id, Email email){
        onPrepareUpdate(id, email);
        
        emailValidator.validateInsertOrUpdate(email);
        
        getRepository().save(email);
    }
    
    @Override
    public List<Email> listar(Integer idUsuario) {
        Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);
        
        return getRepository(EmailRepository.class).findAllByUsuario(usuario);
    }
    
    @Override
    public void inativar(Integer idUsuario, Integer idEmail) {
        Email email = findAndValidate(idEmail);
        
        emailValidator.validateDelete(email);
        
        onPrepareDelete(email);
        
        getRepository().save(email);
    }

    private void onPrepareInsert(Integer idUsuario, Email email) {
        Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);
        
        email.setAtivo(true);
        email.setDataAtualizacao(DateUtil.getDate());
        email.setDataInclusao(DateUtil.getDate());
        email.setDescricao(email.getDescricao().toLowerCase());
        email.setUsuario(usuario);
    }

    private void onPrepareUpdate(Integer id, Email email) {
        Email managedEmail = findAndValidate(id);
        
        email.setId(managedEmail.getId());
        email.setDataInclusao(managedEmail.getDataInclusao());
        email.setUsuario(managedEmail.getUsuario());
        email.setDataAtualizacao(DateUtil.getDate());
        email.setAtivo(true);
    }

    private void onPrepareDelete(Email email) {
        email.setAtivo(false);
        email.setDataAtualizacao(DateUtil.getDate());
    }

}
