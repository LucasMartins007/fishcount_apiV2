package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.TituloParcela;

/**
 *
 * @author Lucas Martins
 */
public interface CustomTituloParcelaRepository {

    TituloParcela findByPagamentoParcela(PagamentoParcela pagamentoParcela);
}
