package com.eventmgt.ticket.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eventmgt.ticket.exception.ticketNotFoundException;
import com.eventmgt.ticket.model.Event;
import com.eventmgt.ticket.model.Ticket;
import com.eventmgt.ticket.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository repo;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Override
	public Ticket bookticket(Ticket addticket) {
		Event event = restTemplate.getForObject("http://localhost:8091/event/get/" + addticket.getEventId(),
				Event.class);
		String ticketType = addticket.getTickettype();
		int quantity = addticket.getQuantity();
		Map<String, Double> ticketTypesPrices = event.getTicketTypesPrices();

		if (ticketTypesPrices.containsKey(ticketType)) {
			Double ticketprice = ticketTypesPrices.get(ticketType) * quantity;
			addticket.setPrice(ticketprice);
		}
		Ticket ticket = repo.save(addticket);
		ticket.setEventdetails(event);
		return ticket;
	}

	@Override
	public Ticket getticketbyid(long ticketId) {
		if (repo.existsById(ticketId)) {
			Ticket ticket = repo.findById(ticketId).get();
			Event event = restTemplate.getForObject("http://localhost:8091/event/get/" + ticket.getEventId(),
					Event.class);
			ticket.setEventdetails(event);
			logger.info("Ticket found with ID: " + ticketId);
			return ticket;

		} else {
			logger.warn("Ticket with ID {} not found", ticketId);
			throw new ticketNotFoundException("ticket not found with ID: " + ticketId);
		}

	}

}
