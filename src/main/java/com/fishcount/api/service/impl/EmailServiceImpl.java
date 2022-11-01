package com.fishcount.api.service.impl;

import com.fishcount.api.repository.EmailRepository;
import com.fishcount.api.service.EmailService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.api.validators.EmailValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.enums.EnumTipoEmail;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.Utils;
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

    private final EmailRepository emailRepository;

    
    @Override
    public Email findByEmail(Email email) {
        return emailRepository.findAtivoByDescricao(email.getDescricao());
    }

    @Override
    public Email incluir(Pessoa pessoa, Email email) {
        onPrepareInsert(pessoa, email);
        
        emailValidator.validateInsertOrUpdate(email);
        
        return emailRepository.save(email);
    }
    
    @Override
    public void editar(Integer id, Email email){
        onPrepareUpdate(id, email);
        
        emailValidator.validateInsertOrUpdate(email);

        emailRepository.save(email);
    }
    
    @Override
    public List<Email> listar(Integer pessoaId) {
        return emailRepository.findAllByPessoa(pessoaId);
    }
    
    @Override
    public void inativar(Integer pessoaId, Integer emailId) {
        final Email email = emailRepository
                .findById(emailId)
                .orElseThrow(() -> new FcRuntimeException(EnumFcInfraException.ENTITY_NOT_FOUND, Email.class.getSimpleName(), emailId));
        
        emailValidator.validateDelete(email);
        
        onPrepareDelete(email);

        emailRepository.save(email);
    }

    public void onPrepareInsert(Pessoa pessoa, Email email) {
        email.setAtivo(true);
        email.setDataAtualizacao(DateUtil.getDate());
        email.setDataInclusao(DateUtil.getDate());
        email.setDescricao(email.getDescricao() != null ? email.getDescricao().toLowerCase() : null);
        email.setPessoa(pessoa);
    }

    @Override
    public void onPrepareUpdate(Integer id, Email email) {
        final Email managedEmail = emailRepository
                .findById(id)
                .orElseThrow(() -> new FcRuntimeException(EnumFcInfraException.ENTITY_NOT_FOUND, Email.class.getSimpleName(), id));

        email.setId(id);
        email.setDataInclusao(managedEmail.getDataInclusao());
        email.setPessoa(managedEmail.getPessoa());
        email.setDataAtualizacao(DateUtil.getDate());
        email.setAtivo(true);
    }

    @Override
    public void validarInsertOrUpdate(Email email){
        emailValidator.validateInsertOrUpdate(email);
    }

    @Override
    public Email encontrarEmailPrincipal(Pessoa pessoa) {
        return ListUtil.stream(pessoa.getEmails())
                .filter(EnumTipoEmail::isPrincipal)
                .findFirst()
                .orElseThrow(() -> new FcRuntimeException(EnumFcDomainException.EMAIL_PRINCIPAL_NAO_INFORMADO));
    }

    @Override
    public void validarEmails(Pessoa pessoa) {
        ListUtil.stream(pessoa.getEmails())
                .forEach(email -> {
                    email.setPessoa(pessoa);
                    emailValidator.validateInsertOrUpdate(email);

                    if (Utils.isNotEmpty(email.getId())) {
                        onPrepareUpdate(email.getId(), email);
                    }
                });
    }

    private void onPrepareDelete(Email email) {
        email.setAtivo(false);
        email.setDataAtualizacao(DateUtil.getDate());
    }

}
