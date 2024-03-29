package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomTituloParcelaRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.TituloParcelaSpec;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.TituloParcela;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class TituloParcelaRepositoryImpl extends RepositoryImpl<TituloParcela, Integer> implements CustomTituloParcelaRepository {

    @Override
    public TituloParcela findByPagamentoParcela(PagamentoParcela pagamentoParcela) {
        return getSpecRepository()
                .findOne(TituloParcelaSpec.parcelaByParcelaPagamento(pagamentoParcela))
                .orElse(null);
    }

}
