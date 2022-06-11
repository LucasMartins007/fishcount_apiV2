package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.financeiro.pix.CobrancaPix;

/**
 *
 * @author Lucas Martins
 */
public interface CustomCobrancaPixRepository {
    
    CobrancaPix findByPagamentoParcela(Integer idParcela);

}
