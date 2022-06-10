package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.CobrancaPixDTO;
import com.fishcount.common.model.entity.CobrancaPix;
import com.fishcount.common.model.entity.PagamentoParcela;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public interface CobrancaPixService extends IAbstractService<CobrancaPix, Integer, CobrancaPixDTO> {

    @Async
    CobrancaPix gerarRegistoCobrancaPix(PagamentoParcela parcela);
}
