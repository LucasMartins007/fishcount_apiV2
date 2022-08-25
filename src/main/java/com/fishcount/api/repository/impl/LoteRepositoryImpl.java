package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomLoteRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.LoteSpec;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;
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
    public List<Lote> findAllAtivosByPessoa(Pessoa pessoa) {
        return getSpecRepository()
                .findAll(
                        LoteSpec.byAtivo()
                                .and(LoteSpec.byPessoa(pessoa))
                                .and(LoteSpec.orderBy(true, "descricao")));
    }

    @Override
    public List<Lote> findAllAtivosByPessoaOrderBy(Pessoa pessoa, String field) {
        return getSpecRepository().findAll(LoteSpec.byAtivo()
                .and(LoteSpec.byPessoa(pessoa))
                .and(LoteSpec.orderBy(true, field)));
    }

}
