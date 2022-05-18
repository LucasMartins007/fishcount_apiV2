package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.UsuarioDTO;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public interface UsuarioService extends IAbstractService<Usuario, Integer, UsuarioDTO>{
 
    Usuario incluir(Usuario usuario);

    Usuario encontrarPorId(Integer id);
    
    Usuario findByEmail(String email);
}
