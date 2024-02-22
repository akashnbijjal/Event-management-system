package com.eventmgt.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmgt.ticket.model.Ticket;
import com.eventmgt.ticket.service.TicketServiceImpl;

@RestController
@RequestMapping("ticket")
public class TicketController {

	@Autowired
	private TicketServiceImpl service;

	@PostMapping("bookticket")
	public ResponseEntity<Ticket> bookticket(@RequestBody Ticket ticket) {
		Ticket ticket2 = service.bookticket(ticket);
		return new ResponseEntity<Ticket>(ticket2, HttpStatus.CREATED);
	}

	@GetMapping("get/{ticketId}")
	public ResponseEntity<Ticket> ticketbyid(@PathVariable("ticketId") long ticketId) {
		Ticket ticket = service.getticketbyid(ticketId);
		return new ResponseEntity<Ticket>(ticket, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("cancel/{ticketId}")
	public ResponseEntity<String> cancelTicket(@PathVariable("ticketId") long ticketId) {
		String ticket = service.cancelTicket(ticketId);
		return new ResponseEntity<String>(ticket, HttpStatus.ACCEPTED);
	}
}
