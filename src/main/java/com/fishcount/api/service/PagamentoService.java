package com.fishcount.api.service;

import com.fishcount.common.model.dto.financeiro.PagamentoDTO;
import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public interface PagamentoService extends IAbstractService<Pagamento, Integer, PagamentoDTO> {

    Pagamento incluir(Pagamento pagamento, Integer idUsuario);

    List<Pagamento> listarPagamentos(Integer idUsuario);

    Pagamento consultarCobranca(Integer idUsuario, Integer id);
}
