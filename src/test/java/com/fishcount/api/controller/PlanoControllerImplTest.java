package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.PlanoControllerImpl;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.converter.Converter;
import com.fishcount.api.service.PlanoService;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.Plano;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import com.fishcount.common.utils.ListUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversion;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlanoControllerImplTest extends AbstractMockController {

    @InjectMocks
    private PlanoControllerImpl planoController;

    @Mock
    private PlanoService planoService;

    private Plano plano;

    private PlanoDTO planoDTO;

    private final String url = "/" + PlanoController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        plano = new Plano();
        planoDTO = new PlanoDTO();
        mockMvc = MockMvcBuilders
                .standaloneSetup(planoController)
                .build();
    }

    @Test
    void testarEndpoint_ListarPlanos() throws Exception {
        when(planoService.listarPlanos())
                .thenReturn(ListUtil.toList(plano));

        MvcResult result = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(ListUtil.toList(planoDTO)), result.getResponse().getContentAsString());
    }

    @Test
    void testarEndpoint_EncontrarPlanos() throws Exception {
        when(planoService.findAndValidate(1))
                .thenReturn(plano);

        MvcResult result = mockMvc.perform(get(url + OperationsPath.ID, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(planoDTO), result.getResponse().getContentAsString());
    }

    @Test
    void testarEndpoint_IncluirPlanos() throws Exception {
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(planoDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
