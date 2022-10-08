package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.PagamentoParcelaControllerImpl;
import com.fishcount.api.service.PagamentoParcelaService;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import com.fishcount.common.utils.ListUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PagamentoParcelaControllerImplTest extends AbstractMockController {

    @InjectMocks
    private PagamentoParcelaControllerImpl pagamentoParcelaController;


    @Mock
    private PagamentoParcelaService pagamentoParcelaService;

    private PagamentoParcela pagamentoParcela;

    private PagamentoParcelaDTO pagamentoParcelaDTO;

    private QRCodePix qrCodePix;

    private QRCodePixDTO qrCodePixDTO;

    private final String url = "/" + PagamentoParcelaController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pagamentoParcelaDTO = new PagamentoParcelaDTO();
        pagamentoParcela = new PagamentoParcela();
        qrCodePix = new QRCodePix();
        qrCodePixDTO = new QRCodePixDTO();
        mockMvc = MockMvcBuilders
                .standaloneSetup(pagamentoParcelaController)
                .build();
    }

    @Test
    void testarEndpoint_ListarPagamentoParcelasSemStatus() throws Exception {
        when(pagamentoParcelaService.listarParcelas(1, 1, null))
                .thenReturn(ListUtil.toList(pagamentoParcela));

        when(mapper.map(pagamentoParcela, PagamentoParcelaDTO.class))
                .thenReturn(pagamentoParcelaDTO);

        MvcResult result = mockMvc.perform(get(url, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(ListUtil.toList(pagamentoParcelaDTO)), result.getResponse().getContentAsString());
    }

    @Test
    void testarEndpoint_ListarPagamentoParcelasComStatus() throws Exception {
        when(pagamentoParcelaService.listarParcelas(1, 1, EnumStatusPagamento.ANALISE))
                .thenReturn(ListUtil.toList(pagamentoParcela));

        when(mapper.map(pagamentoParcela, PagamentoParcelaDTO.class))
                .thenReturn(pagamentoParcelaDTO);

        MvcResult result = mockMvc.perform(get(url, 1, 1)
                        .queryParam("status", EnumStatusPagamento.ANALISE.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(ListUtil.toList(pagamentoParcelaDTO)), result.getResponse().getContentAsString());
    }

    @Test
    void testarEndpoint_EncontrarPagamento() throws Exception {
        when(pagamentoParcelaService.consultarParcela(1, 1, 1))
                .thenReturn(pagamentoParcela);

        when(mapper.map(pagamentoParcela, PagamentoParcelaDTO.class))
                .thenReturn(pagamentoParcelaDTO);

        MvcResult result = mockMvc.perform(get(url + OperationsPath.CHILD_ID, 1, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(pagamentoParcelaDTO), result.getResponse().getContentAsString());
    }

    @Test
    void testarEndpoint_GerarQRCode() throws Exception {
        when(pagamentoParcelaService.gerarQRCodeByParcela(1, 1))
                .thenReturn(qrCodePix);

        when(mapper.map(qrCodePix, QRCodePixDTO.class))
                .thenReturn(qrCodePixDTO);

        MvcResult result = mockMvc.perform(put(url + OperationsPath.CHILD_ID, 1, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(qrCodePixDTO), result.getResponse().getContentAsString());
    }
}
