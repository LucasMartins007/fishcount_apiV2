package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomTaxaCrescimentoRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.api.repository.infrastructure.spec.TaxaCrescimentoSpec;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.TaxaCrescimento;
import org.springframework.stereotype.Repository;

@Repository
public class TaxaCrescimentoRepositoryImpl extends GenericImpl<TaxaCrescimento, Integer> implements CustomTaxaCrescimentoRepository {

    @Override
    public TaxaCrescimento findByEspecie(Especie especie) {
        return getSpecRepository()
                .findOne(TaxaCrescimentoSpec.taxaByEspecie(especie))
                .orElse(null);
    }

}
