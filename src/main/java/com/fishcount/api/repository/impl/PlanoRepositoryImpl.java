package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomPlanoRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.PlanoSpec;
import com.fishcount.common.model.entity.financeiro.Plano;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanoRepositoryImpl extends RepositoryImpl<Plano, Integer> implements CustomPlanoRepository {

    @Override
    public List<Plano> findAllAtivos() {
        return getSpecRepository()
                .findAll(PlanoSpec.byAtivoTrue().and(PlanoSpec.orderBy(true, "descricao")));
    }
}
