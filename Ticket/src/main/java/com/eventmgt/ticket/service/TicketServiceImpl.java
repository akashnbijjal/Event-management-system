package com.eventmgt.ticket.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eventmgt.ticket.exception.EventNotFound;
import com.eventmgt.ticket.exception.ticketNotFoundException;
import com.eventmgt.ticket.feign.EventClient;
import com.eventmgt.ticket.model.Event;
import com.eventmgt.ticket.model.Ticket;
import com.eventmgt.ticket.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	private TicketRepository repo;

	@Autowired
	private EventClient eventclient;

	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Override
	public Ticket bookticket(Ticket addticket) {

		Event event = eventclient.getEvent(addticket.getEventId());
		if (addticket.getEventId() == event.getEventId()) {
			String ticketType = addticket.getTickettype();
			int quantity = addticket.getQuantity();
			Map<String, Double> ticketTypesPrices = event.getTicketTypesPrices();

			if (ticketTypesPrices.containsKey(ticketType)) {
				Double ticketprice = ticketTypesPrices.get(ticketType) * quantity;
				addticket.setPrice(ticketprice);
			}
			addticket.setTicketId(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME));
			Ticket ticket = repo.save(addticket);
			ticket.setEventdetails(event);
			return ticket;
		} else {
			throw new EventNotFound("Event not found with ID: " + addticket.getEventId());
		}
	}

	@Override
	public Ticket getticketbyid(long ticketId) {
		if (repo.existsById(ticketId)) {
			Ticket ticket = repo.findById(ticketId).get();

			Event event = eventclient.getEvent(ticket.getEventId());
			ticket.setEventdetails(event);
			logger.info("Ticket found with ID: " + ticketId);
			return ticket;

		} else {
			logger.warn("Ticket with ID {} not found", ticketId);
			throw new ticketNotFoundException("ticket not found with ID: " + ticketId);
		}

	}

	@Override
	public String cancelTicket(long ticketId) {
		if (repo.existsById(ticketId)) {
			repo.deleteById(ticketId);
			logger.info("Ticket cancelled with ID: " + ticketId);
			return "Ticket deleted successfully ID: " + ticketId;
		} else {
			logger.warn("Ticket with ID {} not found", ticketId);
			throw new ticketNotFoundException("ticket not found with ID: " + ticketId);
		}
	}

}
