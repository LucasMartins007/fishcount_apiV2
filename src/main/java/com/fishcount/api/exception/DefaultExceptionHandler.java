package com.fishcount.api.exception;

import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.RespostaException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.utils.LoggerUtil;
import com.fishcount.common.utils.StackTraceUtil;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author lucas
 */
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<RespostaException> handleException(Exception ex) {
        final String stackTrace = StackTraceUtil.getStackTrace(ex);

        LoggerUtil.getLogger(DefaultExceptionHandler.class).error(stackTrace);

        final RespostaException respostaException = new RespostaException(EnumFcInfraException.NULL_POINTER_EXCEPTION.getMessage());
        respostaException.setTrace(stackTrace);

        return exception(respostaException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
        return exception(ex.getResponseBodyAsString());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object> handleHttpServerErrorException(HttpServerErrorException ex) {
        return exception(ex.getResponseBodyAsString());
    }

    @ExceptionHandler(FcRuntimeException.class)
    public ResponseEntity<?> handleFcRuntimeException(FcRuntimeException ex) {
        return exception(ex);
    }

    @ExceptionHandler(DataException.class)
    public ResponseEntity<?> handleSmartRuntimeExceptionA(DataException ex) {
        return exception(ex.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity exception(Throwable e) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new RespostaException(e));
    }

    private ResponseEntity exception(String e) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(e);
    }

    private ResponseEntity exception(Throwable e, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new RespostaException(e));
    }

    private ResponseEntity exception(RespostaException respostaError, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(respostaError);
    }

}
