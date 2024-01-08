package com.eventmgt.event.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleallException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(EventAlreadyExists.class)
	public final ResponseEntity<ErrorDetails> handleeventalreadyException(EventAlreadyExists ex, WebRequest request)
			throws EventAlreadyExists {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMsg(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(EventNotFound.class)
	public final ResponseEntity<ErrorDetails> handleeventnotfoundException(EventNotFound ex, WebRequest request)
			throws EventNotFound {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMsg(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);

	}
}
