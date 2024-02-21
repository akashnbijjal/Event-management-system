package com.evt_mgt.security.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Exceptioninterceptor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), request.getDescription(false),
				ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleusernameException(UsernameNotFoundException ex, WebRequest request)
			throws UsernameNotFoundException {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), request.getDescription(false),
				ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_GATEWAY);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));
		return handleExceptionInternal(ex, errorDetails, headers, status, request);
	}

}
