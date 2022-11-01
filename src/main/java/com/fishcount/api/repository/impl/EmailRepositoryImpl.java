package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomEmailRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.EmailSpec;
import com.fishcount.common.model.entity.Email;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class EmailRepositoryImpl extends RepositoryImpl<Email, Integer> implements CustomEmailRepository {

    @Override
    public Email findAtivoByDescricao(String descricao) {
        return getSpecRepository()
                .findOne(
                        EmailSpec.emailAtivo()
                                .and(EmailSpec.emailByDescricao(descricao)))
                .orElse(null);
    }

    @Override
    public List<Email> findAllByPessoa(Integer pessoaId) {
        return getSpecRepository()
                .findAll(
                        EmailSpec.emailAtivo()
                        .and(EmailSpec.emailFromPessoa(pessoaId))
                );
    }

}
