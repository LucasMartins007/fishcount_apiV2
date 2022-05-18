package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.TaxaCrescimentoDTO;
import com.fishcount.common.model.entity.TaxaCrescimento;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
public interface TaxaCrescimentoService extends IAbstractService<TaxaCrescimento, Integer, TaxaCrescimentoDTO> {
    
    TaxaCrescimento incluir(TaxaCrescimento taxaCrescimento);

}
