package com.fishcount.api.service;

import com.fishcount.common.model.dto.PagamentoDTO;
import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.entity.Pagamento;
import com.fishcount.common.model.entity.TituloParcela;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public interface PagamentoService extends IAbstractService<Pagamento, Integer, PagamentoDTO> {

    Pagamento incluir(TituloParcela tituloParcela, Pagamento pagamento);
}
