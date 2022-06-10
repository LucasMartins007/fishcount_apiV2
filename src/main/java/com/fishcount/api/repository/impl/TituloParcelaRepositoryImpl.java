package com.fishcount.api.repository.impl;

import com.fishcount.api.infrastructure.spec.TituloParcelaSpec;
import com.fishcount.api.repository.custom.CustomTituloParcelaRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.PagamentoParcela;
import com.fishcount.common.model.entity.TituloParcela;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class TituloParcelaRepositoryImpl extends GenericImpl<TituloParcela, Integer> implements CustomTituloParcelaRepository {

    @Override
    public TituloParcela findByPagamentoParcela(PagamentoParcela pagamentoParcela) {
        return getSpecRepository()
                .findOne(TituloParcelaSpec.parcelaByParcelaPagamento(pagamentoParcela))
                .orElse(null);
    }

}
