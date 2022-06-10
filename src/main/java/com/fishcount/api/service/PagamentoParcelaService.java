package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.PagamentoParcelaDTO;
import com.fishcount.common.model.entity.Pagamento;
import com.fishcount.common.model.entity.PagamentoParcela;
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
