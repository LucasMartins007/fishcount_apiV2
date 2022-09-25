package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.EmailService;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmailControllerImpl extends AbstractController<EmailService> implements com.fishcount.api.controller.EmailController {

    private final EmailService emailService;

    @Override
    public EmailDTO incluir(Integer pessoaId, EmailDTO emailDTO) {
        final Email email = converterDTOParaEntity(emailDTO, Email.class);
        
        return converterEntityParaDTO(emailService.incluir(pessoaId, email), EmailDTO.class);
    }

    @Override
    public void editar(Integer pessoaId, Integer idEmail, EmailDTO emailDTO) {
        Email email = converterDTOParaEntity(emailDTO, Email.class);

        emailService.editar(idEmail, email);
    }

    @Override
    public List<EmailDTO> listar(Integer pessoaId) {
        return converterEntityParaDTO(emailService.listar(pessoaId), EmailDTO.class);
    }

    @Override
    public EmailDTO encontrar(Integer pessoaId, Integer idEmail) {
        return converterEntityParaDTO(emailService.findAndValidate(idEmail), EmailDTO.class);
    }

    @Override
    public void inativar(Integer pessoaId, Integer emailId) {
        emailService.inativar(pessoaId, emailId);
    }

}
