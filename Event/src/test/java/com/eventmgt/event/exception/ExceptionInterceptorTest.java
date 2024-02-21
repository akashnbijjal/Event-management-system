package com.eventmgt.event.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@SpringBootTest
class ExceptionInterceptorTest {

	@MockBean
	private ExceptionInterceptor interceptor;

	@Test
	void test_handleallexception() throws Exception {
		ExceptionInterceptor interceptor = new ExceptionInterceptor();
		WebRequest request = mock(WebRequest.class);

		when(request.getDescription(false)).thenReturn("Test request");
		Exception exception = new Exception("Test exception");

		ResponseEntity<ErrorDetails> response = interceptor.handleallException(exception, request);
		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDetails());
	}

	@Test
	void test_handleeventalreadyException() {
		ExceptionInterceptor interceptor = new ExceptionInterceptor();
		WebRequest request = mock(WebRequest.class);

		when(request.getDescription(false)).thenReturn("Test request");
		EventAlreadyExists exception = new EventAlreadyExists("Test exception");
		ResponseEntity<ErrorDetails> response = interceptor.handleeventalreadyException(exception, request);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDetails());
	}

	@Test
	void test_handleeventnotfoundException() {
		ExceptionInterceptor interceptor = new ExceptionInterceptor();
		WebRequest request = mock(WebRequest.class);

		when(request.getDescription(false)).thenReturn("Test request");
		EventNotFound exception = new EventNotFound("Test exception");
		ResponseEntity<ErrorDetails> response = interceptor.handleeventnotfoundException(exception, request);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDetails());
	}

}
