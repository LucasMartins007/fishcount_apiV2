package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public interface PagamentoParcelaService extends IAbstractService<PagamentoParcela, Integer, PagamentoParcelaDTO> {

    List<PagamentoParcela> incluirParcelas(Pagamento pagamento);

}
