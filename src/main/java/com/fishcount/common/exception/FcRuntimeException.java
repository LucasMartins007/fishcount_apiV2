package com.fishcount.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FcRuntimeException extends RuntimeException {

    private final List<String> details = new ArrayList<>();

    public FcRuntimeException() {
    }

    public FcRuntimeException(String message) {
        super(message);
    }

    public FcRuntimeException(String message, Object... args) {
        this(MessageExceptionBundle.getMensagem(message, args));
    }

    public FcRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FcRuntimeException(IFcException messageTemplate, Throwable cause) {
        this(MessageExceptionBundle.getMensagem(messageTemplate), cause);
    }

    public FcRuntimeException(IFcException messageTemplate) {
        this(MessageExceptionBundle.getMensagem(messageTemplate));
    }

    public FcRuntimeException(IFcException messageTemplate, List<String> details) {
        this(MessageExceptionBundle.getMensagem(messageTemplate));
        this.details.addAll(details);
    }

    public FcRuntimeException(IFcException messageTemplate, Object... args) {
        this(MessageExceptionBundle.getMensagem(messageTemplate, args));
    }

    public FcRuntimeException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public FcRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public List<String> getDetails() {
        return details;
    }

    @FunctionalInterface
    public interface Check<T> {
        T checked();
    }

    public static <T> T checked(Check<T> next) {
        try {
            return next.checked();
        } catch (Exception e) {
            throw new FcRuntimeException(e);
        }
    }

}
