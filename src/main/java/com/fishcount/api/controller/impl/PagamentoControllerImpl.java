package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.PagamentoController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.PagamentoService;
import com.fishcount.common.model.dto.financeiro.PagamentoDTO;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PagamentoControllerImpl
        extends AbstractController<PagamentoService>
        implements PagamentoController {

    private final PagamentoService pagamentoService;

    @Override
    public PagamentoDTO incluir(Integer pessoaId, PagamentoDTO pagamentoDTO) {
        final Pagamento pagamento = pagamentoService.incluir(converterDTOParaEntity(pagamentoDTO, Pagamento.class), pessoaId);

        return converterEntityParaDTO(pagamento, PagamentoDTO.class);
    }

    @Override
    public List<PagamentoDTO> listarParcelas(Integer pessoaId) {
        final List<Pagamento> pagamentos = pagamentoService.listarPagamentos(pessoaId);

        return converterEntityParaDTO(pagamentos, PagamentoDTO.class);
    }

    @Override
    public PagamentoDTO encontrar(Integer pessoaId, Integer pagamentoId) {
        final Pagamento pagamento = pagamentoService.consultarCobranca(pessoaId, pagamentoId);

        return converterEntityParaDTO(pagamento, PagamentoDTO.class);
    }

    @Override
    public List<PagamentoParcelaDTO> listarParcelas(Integer pessoaId, EnumStatusPagamento statusPagamento) {
        return converterEntityParaDTO(pagamentoService.listarParcelas(pessoaId, statusPagamento), PagamentoParcelaDTO.class);
    }

}
