package com.eventmgt.event.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Venue {
	@Id
	private long venueId;

	private String name;

	private String address;

	private String contactEmail;

	private String contactPhone;
}
