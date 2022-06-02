package com.fishcount.api.service.impl;

import com.fishcount.api.service.PagamentoService;
import com.fishcount.common.model.dto.PagamentoDTO;
import com.fishcount.common.model.entity.Pagamento;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceImpl extends AbstractServiceImpl<Pagamento, Integer, PagamentoDTO> implements PagamentoService {

}
