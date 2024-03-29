package com.eventmgt.venue.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "venue")
public class Venue {

	@Transient
	public static final String SEQUENCE_NAME = "venue_sequence";
	@Id
	private long venueId;

	private String name;

	private String address;

	private int capacity;

	private String facilities;

	private double rentalCost;

	private String contactEmail;

	private String contactPhone;
}
