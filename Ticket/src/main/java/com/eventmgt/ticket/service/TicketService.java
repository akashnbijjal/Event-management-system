package com.eventmgt.ticket.service;

import com.eventmgt.ticket.model.Ticket;

public interface TicketService {

	Ticket bookticket(Ticket addticket);

	Ticket getticketbyid(long ticketId);
	
	

}
