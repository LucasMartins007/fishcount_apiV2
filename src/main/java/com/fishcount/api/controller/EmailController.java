package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.IEmailController;
import com.fishcount.api.service.EmailService;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController extends AbstractController<EmailService> implements IEmailController {

    @Override
    public EmailDTO incluir(Integer idUsuario, EmailDTO emailDTO) {
        Email email = converterDTOParaEntity(emailDTO, Email.class);
        
        return converterEntityParaDTO(getService().incluir(idUsuario, email), EmailDTO.class);
    }

    @Override
    public void editar(Integer idUsuario, Integer idEmail, EmailDTO emailDTO) {
        Email email = converterDTOParaEntity(emailDTO, Email.class);
        
        getService().editar(idEmail, email);
    }

    @Override
    public List<EmailDTO> listar(Integer idUsuario) {
        return converterEntityParaDTO(getService().listar(idUsuario), EmailDTO.class);
    }

    @Override
    public EmailDTO encontrar(Integer idUsuario, Integer idEmail) {
        return converterEntityParaDTO(getService().findAndValidate(idEmail), EmailDTO.class);
    }

    @Override
    public void inativar(Integer idUsuario, Integer idEmail) {
        getService().inativar(idUsuario, idEmail);
    }

}
