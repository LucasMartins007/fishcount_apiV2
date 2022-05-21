package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomEmailRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.api.repository.spec.EmailSpec;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Usuario;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class EmailRepositoryImpl extends GenericImpl<Email, Integer> implements CustomEmailRepository {

    @Override
    public Email findAtivoByDescricao(String descricao) {
        return getSpecRepository()
                .findOne(
                        EmailSpec.emailAtivo()
                                .and(EmailSpec.emailByDescricao(descricao)))
                .orElse(null);
    }

    @Override
    public List<Email> findAllByUsuario(Usuario usuario) {
        return getSpecRepository()
                .findAll(
                        EmailSpec.emailAtivo()
                        .and(EmailSpec.emailFromUsuario(usuario))
                );
    }

}
