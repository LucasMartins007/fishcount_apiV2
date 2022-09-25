package com.fishcount.api.controller.generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishcount.api.controller.TelefoneController;
import com.fishcount.common.model.dto.TelefoneDTO;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AbstractMockController {

    protected MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(TelefoneController.class)
//                .build();
//    }

    protected static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
