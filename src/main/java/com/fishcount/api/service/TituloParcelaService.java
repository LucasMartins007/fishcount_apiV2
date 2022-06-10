package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.TituloParcelaDTO;
import com.fishcount.common.model.entity.PagamentoParcela;
import com.fishcount.common.model.entity.TituloParcela;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public interface TituloParcelaService extends IAbstractService<TituloParcela, Integer, TituloParcelaDTO> {
    
    TituloParcela gerarTitulosParcelasByPagamentoParcela(PagamentoParcela parcela);
}
