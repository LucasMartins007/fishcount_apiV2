package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.EmailControllerImpl;
import com.fishcount.api.service.EmailService;
import com.fishcount.api.service.PessoaService;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.pattern.constants.OperationsPath;
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

class EmailControllerImplTest extends AbstractMockController {

    @InjectMocks
    private EmailControllerImpl emailController;

    @Mock
    private EmailService emailService;

    @Mock
    private PessoaService pessoaService;

    private EmailDTO emailDTO;

    private final String url = "/" + EmailController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emailDTO = new EmailDTO();
        mockMvc = MockMvcBuilders
                .standaloneSetup(emailController)
                .build();
    }

    @Test
    void testarEndpoint_IncluirEmail() throws Exception {
        mockMvc.perform(post(url, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(emailDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testarEndpoint_EditarEmail() throws Exception {
        mockMvc.perform(put(url + OperationsPath.ID, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(emailDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_ListarEmail() throws Exception {
        mockMvc.perform(get(url, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_EncontrarEmail() throws Exception {
        mockMvc.perform(get(url + OperationsPath.ID, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_InativarEmail() throws Exception {
        mockMvc.perform(delete(url + OperationsPath.ID, 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}
