package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomLoteRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.LoteSpec;
import com.fishcount.common.model.entity.Lote;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucas Martins
 */
@Repository
public class LoteRepositoryImpl extends RepositoryImpl<Lote, Integer> implements CustomLoteRepository {

    @Override
    public Lote findAtivoByDescricao(String descricao) {
        return getSpecRepository()
                .findOne(
                        LoteSpec.byAtivo()
                                .and(LoteSpec.byDescricao(descricao)))
                .orElse(null);
    }

    @Override
    public List<Lote> findAllAtivosByPessoa(Integer pessoaId) {
        return getSpecRepository()
                .findAll(
                        LoteSpec.byAtivo()
                                .and(LoteSpec.byPessoaId(pessoaId))
                                .and(LoteSpec.orderBy(true, "descricao")));
    }

    @Override
    public List<Lote> findAllAtivosByPessoaOrderBy(Integer pessoaId, String field) {
        return getSpecRepository().findAll(LoteSpec.byAtivo()
                .and(LoteSpec.byPessoaId(pessoaId))
                .and(LoteSpec.orderBy(true, field)));
    }

}
