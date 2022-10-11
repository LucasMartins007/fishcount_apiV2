package com.fishcount.api.controller.generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;

public class AbstractMockController {

    protected MockMvc mockMvc;

    @Mock
    protected ModelMapper mapper;

    protected static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
