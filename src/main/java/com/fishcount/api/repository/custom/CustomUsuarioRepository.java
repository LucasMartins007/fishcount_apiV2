package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Usuario;

/**
 *
 * @author Lucas Martins
 */
public interface CustomUsuarioRepository {
    
    Usuario findByEmailPrincipal(String email);
}
