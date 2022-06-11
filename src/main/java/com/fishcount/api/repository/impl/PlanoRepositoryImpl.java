package com.fishcount.api.repository.impl;

import com.fishcount.api.infrastructure.spec.PlanoSpec;
import com.fishcount.api.repository.custom.CustomPlanoRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.financeiro.Plano;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PlanoRepositoryImpl extends GenericImpl<Plano, Integer> implements CustomPlanoRepository {

    @Override
    public List<Plano> findAllAtivos() {
        return getSpecRepository()
                .findAll(PlanoSpec.byAtivoTrue());
    }
}
