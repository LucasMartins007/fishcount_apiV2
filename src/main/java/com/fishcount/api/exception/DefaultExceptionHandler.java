package com.fishcount.api.exception;

import com.fishcount.common.exception.CommonExceptionResponse;
import com.fishcount.common.exception.FcRuntimeException;
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
    public ResponseEntity<CommonExceptionResponse> handleException(Exception ex) {
        final String stackTrace = StackTraceUtil.getStackTrace(ex);

        LoggerUtil.getLogger(DefaultExceptionHandler.class).error(stackTrace);

        final CommonExceptionResponse respostaException = new CommonExceptionResponse(EnumFcInfraException.NULL_POINTER_EXCEPTION.getMessage());
        respostaException.setTrace(stackTrace);

        return exception(respostaException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        return exception(ex.getResponseBodyAsString());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleHttpServerErrorException(HttpServerErrorException ex) {
        return exception(ex.getResponseBodyAsString());
    }

    @ExceptionHandler(FcRuntimeException.class)
    public ResponseEntity<CommonExceptionResponse> handleFcRuntimeException(FcRuntimeException ex) {
        return exception(ex);
    }

    @ExceptionHandler(DataException.class)
    public ResponseEntity<CommonExceptionResponse> handleSmartRuntimeExceptionA(DataException ex) {
        return exception(ex.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<CommonExceptionResponse> exception(Throwable e) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new CommonExceptionResponse(e));
    }

    private ResponseEntity<String> exception(String e) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(e);
    }

    private ResponseEntity<CommonExceptionResponse> exception(Throwable e, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new CommonExceptionResponse(e));
    }

    private ResponseEntity<CommonExceptionResponse> exception(CommonExceptionResponse respostaError, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(respostaError);
    }

}
