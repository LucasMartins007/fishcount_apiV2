package com.fishcount.common.exception;

import com.fishcount.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author lucas
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(FcRuntimeException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(FcRuntimeException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		RespostaException respostaException = new RespostaException(ex.getMessage());
                respostaException.setDataHora(DateUtil.getDate());
                respostaException.setStatus(status.value());
		
		return handleExceptionInternal(ex, respostaException, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(FcRuntimeException.class)
	public ResponseEntity<Object> handleNegocio(FcRuntimeException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		RespostaException respostaException = new RespostaException(ex.getMessage());
                respostaException.setDataHora(DateUtil.getDate());
                respostaException.setStatus(status.value());
                
		
		return handleExceptionInternal(ex, respostaException, new HttpHeaders(), status, request);
	}
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		
		RespostaException respostaException = new RespostaException(ex.getMessage());
                respostaException.setDataHora(DateUtil.getDate());
                respostaException.setStatus(status.value());
		return super.handleExceptionInternal(ex, respostaException, headers, status, request);
	}

}
