package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.IPagamentoController;
import com.fishcount.api.service.PagamentoService;
import com.fishcount.common.model.dto.financeiro.PagamentoDTO;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PagamentoController
        extends AbstractController<PagamentoService>
        implements IPagamentoController {

    @Override
    public PagamentoDTO incluir(Integer idUsuario, PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = getService().incluir(converterDTOParaEntity(pagamentoDTO, Pagamento.class), idUsuario);

        return converterEntityParaDTO(pagamento, PagamentoDTO.class);
    }

    @Override
    public List<PagamentoDTO> listarPagamentos(Integer idUsuario) {
        List<Pagamento> pagamentos = getService().listarPagamentos(idUsuario);

        return converterEntityParaDTO(pagamentos, PagamentoDTO.class);
    }

    @Override
    public PagamentoDTO consultarPagamento(Integer idUsuario, Integer idPagamento) {
        Pagamento pagamento = getService().consultarCobranca(idUsuario, idPagamento);

        return converterEntityParaDTO(pagamento, PagamentoDTO.class);
    }

    @Override
    public List<PagamentoParcelaDTO> listarParcelas(Integer idUsuario, EnumStatusPagamento statusPagamento) {
        return converterEntityParaDTO(getService().listarParcelas(idUsuario, statusPagamento), PagamentoParcelaDTO.class);
    }

}
