package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.financeiro.TituloParcelaDTO;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.TituloParcela;

/**
 *
 * @author Lucas Martins
 */
public interface TituloParcelaService extends IAbstractService<TituloParcela, Integer, TituloParcelaDTO> {
    
    TituloParcela gerarTitulosParcelasByPagamentoParcela(PagamentoParcela parcela);
}
