package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomUsuarioRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.api.infrastructure.spec.UsuarioSpec;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoEmail;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class UsuarioRepositoryImpl extends GenericImpl<Usuario, Integer> implements CustomUsuarioRepository {

    @Override
    public Usuario findByEmailPrincipal(String email) {
        return getSpecRepository()
                .findOne(
                        UsuarioSpec.usuarioAtivo()
                                .and(UsuarioSpec.usuarioByEmailAndTipo(email, EnumTipoEmail.PRINCIPAL)))
                .orElse(null);
    }
}
