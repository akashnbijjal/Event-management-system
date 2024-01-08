package com.eventmgt.ticket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eventmgt.ticket.model.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, Long> {

}
