package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.financeiro.pix.CobrancaPixDTO;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.pix.CobrancaPix;

/**
 *
 * @author Lucas Martins
 */
public interface CobrancaPixService extends IAbstractService<CobrancaPix, Integer, CobrancaPixDTO> {

    void gerarRegistoCobrancaPix(PagamentoParcela parcela);

    CobrancaPix encontrarCobrancaPorPagamentoParcela(PagamentoParcela parcela);
}
