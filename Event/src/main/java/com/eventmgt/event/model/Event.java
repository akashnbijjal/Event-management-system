package com.eventmgt.event.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Event")
public class Event {

	@Id
	private long eventId;

	private String eventname;
	
	

	private String venue;
	
	private String description;

	private Date startDateTime;
	private Date endDateTime;
	private int maxAttendees;

	private String organizerName;

	private Map<String, Double> ticketTypesPrices;

	@Transient
	private List<Venue> venuedetails;
}
