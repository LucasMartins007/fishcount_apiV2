package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.TituloParcelaControllerImpl;
import com.fishcount.api.service.TituloParcelaService;
import com.fishcount.common.model.dto.UsuarioDTO;
import com.fishcount.common.model.dto.financeiro.TituloParcelaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TitutloParcelaControllerImplTest extends AbstractMockController {

    @InjectMocks
    private TituloParcelaControllerImpl tituloParcelaController;

    @Mock
    private TituloParcelaService tituloParcelaService;

    private TituloParcelaDTO tituloParcelaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tituloParcelaDTO = new TituloParcelaDTO();
        mockMvc = MockMvcBuilders
                .standaloneSetup(tituloParcelaController)
                .build();
    }

    @Test
    void testarEndpoint_AdicionarTituloParcela() throws Exception {
        mockMvc.perform(post("/" + TituloParcelaController.PATH,1,1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tituloParcelaDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testarEndpoint_ListarTitulosParcelas() throws Exception {
        mockMvc.perform(get("/" + TituloParcelaController.PATH,1,1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_LiquidarTituloParcela() throws Exception {
        mockMvc.perform(put("/" + TituloParcelaController.PATH + "/{tituloParcelaId}",1,1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_EncontrarTituloParcela() throws Exception {
        mockMvc.perform(get("/" + TituloParcelaController.PATH + "/{tituloParcelaId}",1,1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
