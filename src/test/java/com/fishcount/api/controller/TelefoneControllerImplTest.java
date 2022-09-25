package com.fishcount.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.TelefoneControllerImpl;
import com.fishcount.api.service.TelefoneService;
import com.fishcount.common.model.dto.TelefoneDTO;
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

class TelefoneControllerImplTest extends AbstractMockController {

    @InjectMocks
    private TelefoneControllerImpl telefoneController;

    @Mock
    private TelefoneService telefoneService;

    private TelefoneDTO telefoneDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        telefoneDTO = new TelefoneDTO();
        mockMvc = MockMvcBuilders
                .standaloneSetup(telefoneController)
                .build();
    }

    @Test
    void testarEndpoint_IncluirTelefone() throws Exception {
        mockMvc.perform(post("/pessoa/{pessoaId}/telefone", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(telefoneDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testarEndpoint_EditarTelefone() throws Exception {
        mockMvc.perform(put("/pessoa/{pessoaId}/telefone/{telefoneId}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(telefoneDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_ListarTelefone() throws Exception {
        mockMvc.perform(get("/pessoa/{pessoaId}/telefone", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_EncontrarTelefone() throws Exception {
        mockMvc.perform(get("/pessoa/{pessoaId}/telefone/{telefoneId}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_InativarTelefone() throws Exception {
        mockMvc.perform(delete("/pessoa/{pessoaId}/telefone/{telefoneId}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
