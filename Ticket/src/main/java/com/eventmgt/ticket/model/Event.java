package com.eventmgt.ticket.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {

	@Id
	private long eventId;

	private String eventname;

	private String venue;

	private String description;
	private Date dateTime;
	private int maxAttendees;

	private String organizerName;

	private Map<String, Double> ticketTypesPrices;
}
