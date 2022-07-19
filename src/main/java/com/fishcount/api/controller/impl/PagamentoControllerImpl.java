package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.PagamentoService;
import com.fishcount.common.model.dto.financeiro.PagamentoDTO;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PagamentoControllerImpl
        extends AbstractController<PagamentoService>
        implements com.fishcount.api.controller.PagamentoController {

    @Override
    public PagamentoDTO incluir(Integer pessoaId, PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = getService().incluir(converterDTOParaEntity(pagamentoDTO, Pagamento.class), pessoaId);

        return converterEntityParaDTO(pagamento, PagamentoDTO.class);
    }

    @Override
    public List<PagamentoDTO> listarParcelas(Integer pessoaId) {
        List<Pagamento> pagamentos = getService().listarPagamentos(pessoaId);

        return converterEntityParaDTO(pagamentos, PagamentoDTO.class);
    }

    @Override
    public PagamentoDTO encontrar(Integer pessoaId, Integer pagamentoId) {
        Pagamento pagamento = getService().consultarCobranca(pessoaId, pagamentoId);

        return converterEntityParaDTO(pagamento, PagamentoDTO.class);
    }

    @Override
    public List<PagamentoParcelaDTO> listarParcelas(Integer pessoaId, EnumStatusPagamento statusPagamento) {
        return converterEntityParaDTO(getService().listarParcelas(pessoaId, statusPagamento), PagamentoParcelaDTO.class);
    }

}
