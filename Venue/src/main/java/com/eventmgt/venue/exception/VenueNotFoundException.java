package com.eventmgt.venue.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VenueNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String msg;

}
