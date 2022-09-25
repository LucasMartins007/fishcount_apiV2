package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.UsuarioControllerImpl;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.common.model.dto.UsuarioDTO;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.pattern.constants.OperationsPath;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UsuarioControllerImplTest extends AbstractMockController {

    @InjectMocks
    private UsuarioControllerImpl usuarioController;

    @Mock
    private UsuarioService usuarioService;

    private UsuarioDTO usuarioDTO;

    private Usuario usuario;

    private final String url = "/" + UsuarioController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioDTO = new UsuarioDTO();
        mockMvc = MockMvcBuilders
                .standaloneSetup(usuarioController)
                .build();
    }

    @Test
    void testarEndpoint_IncluirUsuario() throws Exception {
        mockMvc.perform(post(url + "/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usuarioDTO)))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    void testarEndpoint_EncontrarPorId() throws Exception {
        mockMvc.perform(get(url + OperationsPath.ID, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
