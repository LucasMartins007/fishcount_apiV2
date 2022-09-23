package com.fishcount.api.controller;

import com.fishcount.api.controller.impl.LoteControllerImpl;
import com.fishcount.api.service.LoteService;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoteControllerImplTest {

    @InjectMocks
    private LoteControllerImpl loteController;

    @Mock
    private LoteService loteService;

    private MockMvc mockMvc;

    private LoteDTO loteDTO;

    private Lote lote;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loteDTO = new LoteDTO();
        lote = new Lote();
    }

    @Test
    void testarListagemLotes() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(loteController)
                .build();
        mockMvc.perform(get("/pessoa/{pessoaId}/lote", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarListLotesVazia() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(loteController)
                .build();
        when(loteService.listarFromPessoa(5, null))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/pessoa/{pessoaId}/lote", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarListaLote_DeveChamarService() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(loteController)
                .build();
        loteController.listar(Mockito.any(), Mockito.any());

        verify(loteService, times(1))
                .listarFromPessoa(Mockito.any(), Mockito.any());
    }

    @Test
    void testarRetornoVazio_DeveRetornarUmaListaVazia(){
        when(loteService.listarFromPessoa(Mockito.any(), Mockito.any()))
                .thenReturn(Collections.emptyList());

        final List<LoteDTO> lotes = loteController.listar(1, null);
        Assertions.assertSame(Collections.emptyList(), lotes);
    }
}
