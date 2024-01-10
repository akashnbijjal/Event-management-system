package com.eventmgt.ticket.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "ticket")
public class Ticket {

	@Id
	private long ticketId;

	private long eventId;

	private String tickettype;

	private int quantity;

	private Double price;

	private Date purchasedate;

	private boolean valid;

	@Transient
	private Event eventdetails;

}
