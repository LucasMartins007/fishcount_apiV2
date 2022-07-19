package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomLoteRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.LoteSpec;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class LoteRepositoryImpl extends RepositoryImpl<Lote, Integer> implements CustomLoteRepository {

    @Override
    public Lote findByDescricao(String descricao) {
        return getSpecRepository()
                .findOne(
                        LoteSpec.byDescricao(descricao))
                .orElse(null);
    }

    @Override
    public List<Lote> findAllByPessoa(Pessoa pessoa) {
        return getSpecRepository()
                .findAll(
                        LoteSpec.byPessoa(pessoa)
                                .and(LoteSpec.orderBy(true, "descricao")));
    }

}
