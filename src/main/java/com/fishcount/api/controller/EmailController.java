package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.IEmailController;
import com.fishcount.api.service.EmailService;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailController extends AbstractController<EmailService> implements IEmailController {

    @Override
    public EmailDTO incluir(Integer pessoaId, EmailDTO emailDTO) {
        Email email = converterDTOParaEntity(emailDTO, Email.class);
        
        return converterEntityParaDTO(getService().incluir(pessoaId, email), EmailDTO.class);
    }

    @Override
    public void editar(Integer pessoaId, Integer idEmail, EmailDTO emailDTO) {
        Email email = converterDTOParaEntity(emailDTO, Email.class);
        
        getService().editar(idEmail, email);
    }

    @Override
    public List<EmailDTO> listar(Integer pessoaId) {
        return converterEntityParaDTO(getService().listar(pessoaId), EmailDTO.class);
    }

    @Override
    public EmailDTO encontrar(Integer pessoaId, Integer idEmail) {
        return converterEntityParaDTO(getService().findAndValidate(idEmail), EmailDTO.class);
    }

    @Override
    public void inativar(Integer pessoaId, Integer emailId) {
        getService().inativar(pessoaId, emailId);
    }

}
