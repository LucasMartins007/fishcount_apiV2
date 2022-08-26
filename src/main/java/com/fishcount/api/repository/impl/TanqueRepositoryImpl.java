package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomTanqueRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.TanqueSpec;
import com.fishcount.common.model.entity.Tanque;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TanqueRepositoryImpl extends RepositoryImpl<Tanque, Integer> implements CustomTanqueRepository {

    @Override
    public List<Tanque> findAllByPessoaAndLote(Integer pessoaId, Integer loteId) {
        return getSpecRepository()
                .findAll(TanqueSpec.byLote(loteId)
                        .and(TanqueSpec.byPessoa(pessoaId))
                );
    }

    @Override
    public Tanque findByPessoaAndLoteAndId(Integer pessoaId, Integer loteId, Integer tanqueId) {
        return getSpecRepository()
                .findOne(TanqueSpec.byId(tanqueId)
                        .and(TanqueSpec.byPessoa(pessoaId))
                        .and(TanqueSpec.byLote(loteId))
                ).orElse(null);
    }

    @Override
    public List<Tanque> findAllByPessoaAndLoteOrderBy(Integer pessoaId, Integer loteId, String orderBy) {
        return getSpecRepository()
                .findAll(TanqueSpec.byPessoa(pessoaId)
                        .and(TanqueSpec.byLote(loteId))
                        .and(TanqueSpec.orderBy(true, orderBy)));
    }


}
