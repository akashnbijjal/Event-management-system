package com.eventmgt.ticket.controller;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eventmgt.ticket.feign.EventClient;

import com.eventmgt.ticket.model.Ticket;
import com.eventmgt.ticket.service.TicketServiceImpl;

@SpringBootTest
class TicketControllerTest {

	@MockBean
	private TicketServiceImpl service;

	@Autowired
	private TicketController controller;

	@MockBean
	private EventClient eventClient;

	@Test
	void test_bookticket() {

		Ticket newTicket = new Ticket(1, 1, "VIP", 1, 5000.00, new Date(), true, eventClient.getEvent(1));
		Ticket expectedTicket = new Ticket(1, 1, "VIP", 1, 5000.00, new Date(), true, eventClient.getEvent(1));
		Mockito.when(service.bookticket(newTicket)).thenReturn(expectedTicket);
		ResponseEntity<Ticket> savedTicket = controller.bookticket(newTicket);
		assertEquals(HttpStatus.CREATED, savedTicket.getStatusCode());
		assertEquals(expectedTicket, savedTicket.getBody());
	}

	@Test
	void test_ticketbyid() {
		long ticketId = 1;
		Ticket expectedTicket = new Ticket(1, 1, "VIP", 1, 5000.00, new Date(), true, eventClient.getEvent(1));
		Mockito.when(service.getticketbyid(ticketId)).thenReturn(expectedTicket);
		ResponseEntity<Ticket> response = controller.ticketbyid(ticketId);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(expectedTicket, response.getBody());

	}

}
