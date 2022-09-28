package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.LoginControllerImpl;
import com.fishcount.api.service.LoginService;
import com.fishcount.common.model.dto.AutenticacaoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginControllerImplTest extends AbstractMockController {

    @InjectMocks
    private LoginControllerImpl loginController;

    @Mock
    private LoginService loginService;

    private AutenticacaoDTO autenticacaoDTO;

    private static final String url = "/" + LoginController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        autenticacaoDTO = new AutenticacaoDTO();
        mockMvc = MockMvcBuilders
                .standaloneSetup(loginController)
                .build();
    }

    @Test
    void testarEndpoint_Autenticar() throws Exception {
        when(loginService.autenticar(autenticacaoDTO))
                .thenReturn(autenticacaoDTO);

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(autenticacaoDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
