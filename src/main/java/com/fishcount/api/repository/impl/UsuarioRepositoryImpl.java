package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomUsuarioRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.UsuarioSpec;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class UsuarioRepositoryImpl extends RepositoryImpl<Usuario, Integer> implements CustomUsuarioRepository {

    @Override
    public Usuario findByEmailPrincipal(String email) {
        return getSpecRepository()
                .findOne(
                        UsuarioSpec.usuarioAtivo()
                                .and(UsuarioSpec.usuarioByEmail(email)))
                .orElse(null);
    }
}
