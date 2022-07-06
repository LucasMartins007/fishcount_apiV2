package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.infrastructure.spec.CobrancaPixSpec;
import com.fishcount.api.repository.custom.CustomCobrancaPixRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.financeiro.pix.CobrancaPix;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class CobrancaPixRepositoryImpl extends GenericImpl<CobrancaPix, Integer> implements CustomCobrancaPixRepository {

    @Override
    public CobrancaPix findByPagamentoParcela(Integer idParcela) {
        return getSpecRepository()
                .findOne(CobrancaPixSpec.byPagamentoParcela(idParcela))
                .orElse(null);
    }

}
