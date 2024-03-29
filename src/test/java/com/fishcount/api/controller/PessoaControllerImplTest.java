package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.PessoaControllerImpl;
import com.fishcount.api.service.PessoaService;
import com.fishcount.common.model.dto.PessoaDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PessoaControllerImplTest extends AbstractMockController {

    @InjectMocks
    private PessoaControllerImpl pessoaController;

    @Mock
    private PessoaService pessoaService;
    private PessoaDTO pessoaDTO;

    private Pessoa pessoa;

    private final String url = "/" + PessoaController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaDTO = new PessoaDTO();
        pessoa = new Pessoa();
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
    }

    @Test
    void testarInclusao() throws Exception {
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoaDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testarEdicao() throws Exception {
        mockMvc.perform(put(url + OperationsPath.ID, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoaDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEncontrarPessoa() throws Exception {
        mockMvc.perform(get(url + OperationsPath.ID, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
