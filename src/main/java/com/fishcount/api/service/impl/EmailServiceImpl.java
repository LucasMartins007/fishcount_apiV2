package com.fishcount.api.service.impl;

import com.fishcount.api.repository.EmailRepository;
import com.fishcount.api.service.EmailService;
import com.fishcount.api.validators.EmailValidator;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl extends AbstractServiceImpl<Email, Integer, EmailDTO> implements EmailService {

    private final EmailValidator emailValidator = new EmailValidator();
    
    @Override
    public Optional<Email> findByEmail(Email email) {
        return getRepository(EmailRepository.class).findByDescricaoAndAtivoTrue(email.getDescricao());
    }

    @Override
    public Email incluir(Email email) {
        emailValidator.validateInsert(email);
        
        onPrepareInsert(email);
        
        return getRepository().save(email);
    }

    private void onPrepareInsert(Email email) {
        email.setAtivo(true);
        email.setDataAtualizacao(DateUtil.getDate());
        email.setDataInclusao(DateUtil.getDate());
        email.setDescricao(email.getDescricao().toLowerCase());
    }

}
