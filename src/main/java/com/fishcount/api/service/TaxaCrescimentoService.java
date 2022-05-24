package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.TaxaCrescimentoDTO;
import com.fishcount.common.model.entity.TaxaCrescimento;

/**
 *
 * @author lucas
 */
public interface TaxaCrescimentoService extends IAbstractService<TaxaCrescimento, Integer, TaxaCrescimentoDTO> {
    
    TaxaCrescimento incluir(TaxaCrescimento taxaCrescimento);
    
    TaxaCrescimento findByEspecie(String especie);

}
