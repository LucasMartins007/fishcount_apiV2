package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.EspecieControllerImpl;
import com.fishcount.api.service.EspecieService;
import com.fishcount.common.model.dto.EspecieDTO;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.utils.ListUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EspecieControllerImplTest extends AbstractMockController {

    @InjectMocks
    private EspecieControllerImpl especieController;

    @Mock
    private EspecieService especieService;

    private EspecieDTO especieDTO;

    private Especie especie;

    private final String url = "/" + EspecieController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        especieDTO = new EspecieDTO();
        especie = new Especie();
        mockMvc = MockMvcBuilders
                .standaloneSetup(especieController)
                .build();
    }

    @Test
    void testarEndpoint_ListarEspecies() throws Exception {
        when(especieService.findAll()).thenReturn(ListUtil.toList(especieDTO));

        MvcResult result = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(ListUtil.toList(especieDTO)), result.getResponse().getContentAsString());
    }

    @Test
    void testarEndpoint_EncontrarEspeciePorDescricao() throws Exception {
        when(especieService.findByDescricao(Mockito.anyString())).thenReturn(especie);

        MvcResult result = mockMvc.perform(get(url + "/find")
                        .queryParam("descricao", "tilapia")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(especieDTO), result.getResponse().getContentAsString());
    }

    @Test
    void testarEndpoint_FindFirst() throws Exception {
        when(especieService.findAll())
                .thenReturn(ListUtil.toList(especieDTO));

        MvcResult result = mockMvc.perform(get(url + "/first")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(especieDTO), result.getResponse().getContentAsString());
    }


}
