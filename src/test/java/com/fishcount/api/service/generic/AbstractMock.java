package com.fishcount.api.service.generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishcount.common.exception.MessageExceptionBundle;
import com.fishcount.common.exception.enums.EnumFcDomainException;

public class AbstractMock {

    protected String getException(EnumFcDomainException message, Object... args) {
        return MessageExceptionBundle.getMensagem(message.getMessage(), args);
    }

    protected static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
