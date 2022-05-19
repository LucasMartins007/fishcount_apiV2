package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomUsuarioRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.api.repository.spec.UsuarioSpec;
import com.fishcount.common.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl extends GenericImpl<Usuario, Integer> implements CustomUsuarioRepository {

    @Override
    public Usuario findByEmail(String email) {
        return getSpecRepository()
                .findOne(UsuarioSpec.usuarioAtivo()
                        .and(UsuarioSpec.usuarioByEmail(email)))
                .orElse(null);
    }
}
