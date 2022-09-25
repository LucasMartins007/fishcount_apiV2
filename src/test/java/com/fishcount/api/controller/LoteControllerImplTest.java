package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.LoteControllerImpl;
import com.fishcount.api.service.LoteService;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoteControllerImplTest extends AbstractMockController {

    @InjectMocks
    private LoteControllerImpl loteController;

    @Mock
    private LoteService loteService;

    private LoteDTO loteDTO;

    private final String url = "/" + LoteController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loteDTO = new LoteDTO();
        mockMvc = MockMvcBuilders
                .standaloneSetup(loteController)
                .build();
    }

    @Test
    void testarEndpoint_InativarLotes() throws Exception {
        mockMvc.perform(delete(url + OperationsPath.ID, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void testarEndpoint_AtualizarLotes() throws Exception {
        mockMvc.perform(put(url + OperationsPath.ID, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loteDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_TestarListaLotes() throws Exception {
        mockMvc.perform(get(url, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_DeveRetornarListaVazia() throws Exception {
        when(loteService.listarFromPessoa(5, null))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get(url, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarListaLote_DeveChamarService() {
        loteController.listar(Mockito.any(), Mockito.any());

        verify(loteService, times(1))
                .listarFromPessoa(Mockito.any(), Mockito.any());
    }

    @Test
    void testarRetornoVazio_DeveRetornarUmaListaVazia() {
        when(loteService.listarFromPessoa(Mockito.any(), Mockito.any()))
                .thenReturn(Collections.emptyList());

        final List<LoteDTO> lotes = loteController.listar(1, null);
        Assertions.assertSame(Collections.emptyList(), lotes);
    }
}
