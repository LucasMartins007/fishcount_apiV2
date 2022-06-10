package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.PagamentoParcela;
import com.fishcount.common.model.entity.TituloParcela;

/**
 *
 * @author Lucas Martins
 */
public interface CustomTituloParcelaRepository {

    TituloParcela findByPagamentoParcela(PagamentoParcela pagamentoParcela);
}
