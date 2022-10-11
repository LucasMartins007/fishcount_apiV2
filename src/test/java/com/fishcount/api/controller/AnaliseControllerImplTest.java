package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.AnaliseControllerImpl;
import com.fishcount.api.service.AnaliseService;
import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.model.enums.EnumUnidadePeso;
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


class AnaliseControllerImplTest extends AbstractMockController {

    @InjectMocks
    private AnaliseControllerImpl analiseController;

    @Mock
    private AnaliseService analiseService;

    private AnaliseDTO analiseDTO;

    private Analise analise;

    private final String url = "/" + AnaliseController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        analiseDTO = new AnaliseDTO();
        analise = new Analise();
        mockMvc = MockMvcBuilders
                .standaloneSetup(analiseController)
                .build();
    }

    @Test
    void testarEndpoint_InicioAnalise() throws Exception {
        mockMvc.perform(post(url)
                        .queryParam("tanqueId", "1")
                        .queryParam("pesoAtual", "1.0")
                        .queryParam("temperatura", "20.0")
                        .queryParam("unidadePeso", EnumUnidadePeso.KILO.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testarEndpoint_SimularAnaliseConcluida() throws Exception {
        mockMvc.perform(put(url + OperationsPath.ID, 1)
                        .queryParam("tanqueId", "1")
                        .queryParam("qtdePeixes", "3000")
                        .queryParam("temperatura", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_ListaPorTanque() throws Exception {
        when(analiseService.listarPorTanque(1, EnumStatusAnalise.ANALISE_NAO_REALIZADA))
                .thenReturn(ListUtil.toList(analise));

        when(mapper.map(analise, AnaliseDTO.class))
                .thenReturn(analiseDTO);

        MvcResult result = mockMvc.perform(get(url)
                        .queryParam("tanqueId", "1")
                        .queryParam("statusAnalise", EnumStatusAnalise.ANALISE_NAO_REALIZADA.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(ListUtil.toList(analiseDTO)), result.getResponse().getContentAsString());
    }
}
