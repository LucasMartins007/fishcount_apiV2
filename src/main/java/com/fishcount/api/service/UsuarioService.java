package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.UsuarioDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;

/**
 *
 * @author lucas
 */
public interface UsuarioService extends IAbstractService<Usuario, Integer, UsuarioDTO>{
 
    Usuario incluir(Usuario usuario);

    Usuario gerarUsuario(Pessoa pessoa);

    Usuario encontrarPorId(Integer id);
    
    Usuario findByEmail(String email);
}
