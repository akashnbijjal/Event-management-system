package com.eventmgt.ticket.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eventmgt.ticket.exception.ticketNotFoundException;
import com.eventmgt.ticket.feign.EventClient;
import com.eventmgt.ticket.model.Event;
import com.eventmgt.ticket.model.Ticket;
import com.eventmgt.ticket.repository.TicketRepository;

@SpringBootTest
class TicketServiceImplTest {

	@MockBean
	private TicketRepository ticketRepository;

	@Autowired
	private TicketServiceImpl service;

	@MockBean
	private EventClient eventclient;

	@Test
	void test_bookticket_success() {
		Map<String, Double> ticketTypesPrices = new HashMap<>();
		ticketTypesPrices.put("VIP", 100.0);
		ticketTypesPrices.put("General", 50.0);
		Event eventdetails = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(), 100,
				"Vijay Malya", Collections.singletonMap("VIP", 100.00));
		Ticket newTicket = new Ticket(1, 1, "VIP", 1, 5000.00, new Date(), true, eventdetails);
		Ticket expectedTicket = new Ticket(1, 1, "VIP", 1, 5000.00, new Date(), true, eventdetails);
		Mockito.when(ticketRepository.save(newTicket)).thenReturn(expectedTicket);
		Ticket savedTicket = service.bookticket(expectedTicket);
		assertEquals(expectedTicket, savedTicket);
	}

	@Test
	void test_getticketbyid_success() {
		long ticketId = 1;
		Ticket expectedTicket = new Ticket(1, 1, "VIP", 1, 5000.00, new Date(), true, eventclient.getEvent(ticketId));
		Mockito.when(ticketRepository.existsById(ticketId)).thenReturn(true);
		Mockito.when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(expectedTicket));
		Ticket actualTicket = service.getticketbyid(ticketId);
		assertEquals(expectedTicket, actualTicket);

	}

	@Test
	void test_getticketbyid_failed() {
		long ticketId = -1;
		Mockito.when(ticketRepository.existsById(ticketId)).thenReturn(false);
		ticketNotFoundException exception = assertThrows(ticketNotFoundException.class,
				() -> service.getticketbyid(ticketId));
		assertEquals("ticket not found with ID: " + ticketId, exception.getMsg());
	}

}
