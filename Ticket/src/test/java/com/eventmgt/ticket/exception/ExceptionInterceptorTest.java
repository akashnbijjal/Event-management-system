package com.eventmgt.ticket.exception;

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
	private ExceptionInterceptor exceptionInterceptor;

	@Test
	public void testHandleAllException() throws Exception {

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
	public void test_handleTicketNotFoundException() {
		ExceptionInterceptor interceptor = new ExceptionInterceptor();
		WebRequest request = mock(WebRequest.class);
		when(request.getDescription(false)).thenReturn("Test request");
		ticketNotFoundException exception = new ticketNotFoundException("Test exception");
		ResponseEntity<ErrorDetails> response = interceptor.handleTicketNotFoundException(exception, request);
		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDetails());
	}

	@Test
	public void test_handleEventNotFoundException() {
		ExceptionInterceptor interceptor = new ExceptionInterceptor();
		WebRequest request = mock(WebRequest.class);
		when(request.getDescription(false)).thenReturn("Test request");
		EventNotFound exception = new EventNotFound("Test exception");
		ResponseEntity<ErrorDetails> response = interceptor.handleEventNotFoundException(exception, request);
		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDetails());
	}

}
