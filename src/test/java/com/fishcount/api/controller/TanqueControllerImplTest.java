package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.TanqueControllerImpl;
import com.fishcount.api.service.TanqueService;
import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.entity.financeiro.Plano;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TanqueControllerImplTest extends AbstractMockController {

    @InjectMocks
    private TanqueControllerImpl tanqueController;

    @Mock
    private TanqueService tanqueService;

    private TanqueDTO tanqueDTO;

    private Tanque tanque;

    private final String url = "/" + TanqueController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tanqueDTO = new TanqueDTO();
        tanque = new Tanque();
        mockMvc = MockMvcBuilders
                .standaloneSetup(tanqueController)
                .build();
    }

    @Test
    void testarEndpoint_IncluirTanque() throws Exception {
        mockMvc.perform(post(url, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tanqueDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testarEndpoint_EditarTanque() throws Exception {
        mockMvc.perform(put(url + OperationsPath.ID, 1, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tanqueDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_ListarTanquesFromLoteOrderByNull() throws Exception {
        when(tanqueService.listarFromPessoaAndLote(1, 1, null))
                .thenReturn(ListUtil.toList(tanque));

        MvcResult result = mockMvc.perform(get(url, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(ListUtil.toList(tanqueDTO)), result.getResponse().getContentAsString());
    }

    @Test
    void testarEndpoint_ListarTanquesFromLoteOrderByDescricao() throws Exception {
        when(tanqueService.listarFromPessoaAndLote(1, 1, "descricao"))
                .thenReturn(ListUtil.toList(tanque));

        MvcResult result = mockMvc.perform(get(url, 1, 1)
                        .queryParam("orderBy", "descricao")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(ListUtil.toList(tanqueDTO)), result.getResponse().getContentAsString());
    }

    @Test
    void testarEndpoint_EncontrarPorId() throws Exception {
        when(tanqueService.encontrarPorId(1, 1, 1))
                .thenReturn(tanque);

        MvcResult result = mockMvc.perform(get(url + OperationsPath.ID, 1, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(tanqueDTO), result.getResponse().getContentAsString());
    }

    @Test
    void testarEndpoint_inativarTanque() throws Exception {
        mockMvc.perform(delete(url + OperationsPath.ID, 1, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }



}
