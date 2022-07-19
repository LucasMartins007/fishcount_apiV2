package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomTaxaCrescimentoRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.TaxaCrescimentoSpec;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.TaxaCrescimento;
import org.springframework.stereotype.Repository;

@Repository
public class TaxaCrescimentoRepositoryImpl extends RepositoryImpl<TaxaCrescimento, Integer> implements CustomTaxaCrescimentoRepository {

    @Override
    public TaxaCrescimento findByEspecie(Especie especie) {
        return getSpecRepository()
                .findOne(TaxaCrescimentoSpec.taxaByEspecie(especie))
                .orElse(null);
    }

}
