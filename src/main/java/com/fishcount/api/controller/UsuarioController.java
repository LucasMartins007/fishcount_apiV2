package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.IUsuarioController;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.common.model.dto.UsuarioDTO;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
public class UsuarioController extends AbstractController<UsuarioService> implements IUsuarioController{

    @Override
    public UsuarioDTO incluir(UsuarioDTO usuarioDTO) {
        Usuario usuario = converterDTOParaEntity(usuarioDTO, Usuario.class);
        usuario = getService().incluir(usuario);
        
        return converterEntityParaDTO(usuario, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO findById(Integer id) {
        Usuario usuario = getService().findAndValidate(id);
        
        return converterEntityParaDTO(usuario, UsuarioDTO.class);
    }
    
}
