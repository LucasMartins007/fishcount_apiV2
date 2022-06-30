package com.fishcount.api.service.impl;

import com.fishcount.api.repository.EmailRepository;
import com.fishcount.api.service.EmailService;
import com.fishcount.api.service.PessoaService;
import com.fishcount.api.validators.EmailValidator;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl extends AbstractServiceImpl<Email, Integer, EmailDTO> implements EmailService {

    private final EmailValidator emailValidator;
    
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
    public List<Email> listar(Integer idPessoa) {
        Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);
        
        return getRepository(EmailRepository.class).findAllByPessoa(pessoa);
    }
    
    @Override
    public void inativar(Integer idUsuario, Integer idEmail) {
        Email email = findAndValidate(idEmail);
        
        emailValidator.validateDelete(email);
        
        onPrepareDelete(email);
        
        getRepository().save(email);
    }

    private void onPrepareInsert(Integer idPessoa, Email email) {
        final Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);

        email.setAtivo(true);
        email.setDataAtualizacao(DateUtil.getDate());
        email.setDataInclusao(DateUtil.getDate());
        email.setDescricao(email.getDescricao().toLowerCase());
        email.setPessoa(pessoa);
    }

    private void onPrepareUpdate(Integer id, Email email) {
        Email managedEmail = findAndValidate(id);
        
        email.setId(managedEmail.getId());
        email.setDataInclusao(managedEmail.getDataInclusao());
        email.setPessoa(managedEmail.getPessoa());
        email.setDataAtualizacao(DateUtil.getDate());
        email.setAtivo(true);
    }

    private void onPrepareDelete(Email email) {
        email.setAtivo(false);
        email.setDataAtualizacao(DateUtil.getDate());
    }

}
