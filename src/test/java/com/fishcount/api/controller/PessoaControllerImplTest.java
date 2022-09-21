package com.fishcount.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishcount.api.controller.impl.PessoaControllerImpl;
import com.fishcount.api.service.PessoaService;
import com.fishcount.common.model.dto.PessoaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PessoaControllerImplTest {

    @InjectMocks
    private PessoaControllerImpl pessoaController;

    @Mock
    private PessoaService pessoaService;

    private MockMvc mockMvc;

    private PessoaDTO pessoaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaDTO = new PessoaDTO();
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
    }

    @Test
    void testarInclusao() throws Exception {
        mockMvc.perform(post("/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoaDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testarEdicao() throws Exception {
        mockMvc.perform(put("/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoaDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEncontrarPessoa() throws Exception {
        mockMvc.perform(get("/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
