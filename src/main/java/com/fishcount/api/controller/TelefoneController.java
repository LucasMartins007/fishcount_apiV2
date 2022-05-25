package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.ITelefoneController;
import com.fishcount.api.service.TelefoneService;
import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.entity.Telefone;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelefoneController extends AbstractController<TelefoneService> implements ITelefoneController {

    @Override
    public TelefoneDTO incluir(Integer idUsuario, TelefoneDTO telefoneDTO) {
        Telefone telefone = getService().incluir(idUsuario, converterDTOParaEntity(telefoneDTO, Telefone.class));

        return converterEntityParaDTO(telefone, TelefoneDTO.class);
    }

    @Override
    public void editar(Integer idUsuario, Integer idTelefone, TelefoneDTO telefoneDTO) {
        Telefone telefone = converterDTOParaEntity(telefoneDTO, Telefone.class);

        getService().editar(idUsuario, idTelefone, telefone);
    }

    @Override
    public List<TelefoneDTO> listar(Integer idUsuario) {
        List<Telefone> telefones = getService().listarAtivos(idUsuario);

        return converterEntityParaDTO(telefones, TelefoneDTO.class);
    }

    @Override
    public TelefoneDTO encontrar(Integer idUsuario, Integer idTelefone) {
        return converterEntityParaDTO(getService().findAndValidate(idUsuario), TelefoneDTO.class);
    }

    @Override
    public void inativar(Integer idUsuario, Integer idTelefone) {
        getService().inativar(idUsuario, idTelefone);
    }

}
