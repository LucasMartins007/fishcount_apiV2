package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.TelefoneService;
import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.entity.Telefone;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TelefoneControllerImpl extends AbstractController<TelefoneService> implements com.fishcount.api.controller.TelefoneController {

    private final TelefoneService  telefoneService;

    @Override
    public TelefoneDTO incluir(Integer idUsuario, TelefoneDTO telefoneDTO) {
        final Telefone telefone = telefoneService.incluir(idUsuario, converterDTOParaEntity(telefoneDTO, Telefone.class));

        return converterEntityParaDTO(telefone, TelefoneDTO.class);
    }

    @Override
    public void editar(Integer pessoaId, Integer telefoneId, TelefoneDTO telefoneDTO) {
        final Telefone telefone = converterDTOParaEntity(telefoneDTO, Telefone.class);

        telefoneService.editar(pessoaId, telefoneId, telefone);
    }

    @Override
    public List<TelefoneDTO> listar(Integer idUsuario) {
        final List<Telefone> telefones = telefoneService.listarAtivos(idUsuario);

        return converterEntityParaDTO(telefones, TelefoneDTO.class);
    }

    @Override
    public TelefoneDTO encontrar(Integer idUsuario, Integer idTelefone) {
        return converterEntityParaDTO(telefoneService.findAndValidate(idUsuario), TelefoneDTO.class);
    }

    @Override
    public void inativar(Integer idUsuario, Integer idTelefone) {
        telefoneService.inativar(idUsuario, idTelefone);
    }

}
