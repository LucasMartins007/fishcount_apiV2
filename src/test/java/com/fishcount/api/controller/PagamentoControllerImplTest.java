package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.PagamentoControllerImpl;
import com.fishcount.api.service.PagamentoService;
import com.fishcount.common.model.dto.financeiro.PagamentoDTO;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.utils.ListUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PagamentoControllerImplTest extends AbstractMockController {

    @InjectMocks
    private PagamentoControllerImpl pagamentoController;

    @Mock
    private PagamentoService pagamentoService;

    private PagamentoDTO pagamentoDTO;

    private Pagamento pagamento;

    private PagamentoParcela pagamentoParcela;

    private final String url = "/pessoa/{pessoaId}/pagamento";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pagamentoDTO = new PagamentoDTO();
        pagamento = new Pagamento();
        pagamentoParcela = new PagamentoParcela();
        mockMvc = MockMvcBuilders
                .standaloneSetup(pagamentoController)
                .build();
    }

    @Test
    void testarEndpoint_IncluirPagamento() throws Exception {
        when(pagamentoService.incluir(pagamento, 1))
                .thenReturn(pagamento);

        mockMvc.perform(post(url, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pagamentoDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testarEndpoint_ListarPagamentos() throws Exception {
        when(pagamentoService.listarPagamentos(1))
                .thenReturn(ListUtil.toList(pagamento));

        mockMvc.perform(get(url, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_EncontrarPagamento() throws Exception {
        when(pagamentoService.consultarCobranca(1,1))
                .thenReturn(pagamento);

        mockMvc.perform(get(url + "/{pagamentoId}", 1,1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_ListarParcelas() throws Exception {
        when(pagamentoService.listarParcelas(1, EnumStatusPagamento.ANALISE))
                .thenReturn(ListUtil.toList(pagamentoParcela));

        mockMvc.perform(get(url + "/parcelas", 1)
                        .queryParam("status", EnumStatusPagamento.ANALISE.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
