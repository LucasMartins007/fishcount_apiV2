package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.common.model.dto.UsuarioDTO;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
public class UsuarioControllerImpl extends AbstractController<UsuarioService> implements com.fishcount.api.controller.UsuarioController {

    @Override
    public UsuarioDTO incluir(UsuarioDTO usuarioDTO) {
        Usuario usuario = converterDTOParaEntity(usuarioDTO, Usuario.class);
        usuario = getService().incluir(usuario);
        
        return converterEntityParaDTO(usuario, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO findById(Integer id) {
        Usuario usuario = getService().encontrarPorId(id);
        
        return converterEntityParaDTO(usuario, UsuarioDTO.class);
    }
    
}
