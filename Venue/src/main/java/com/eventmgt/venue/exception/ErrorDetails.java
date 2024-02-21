package com.eventmgt.venue.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDetails {

	private LocalDateTime timestamp;

	private String message;

	private String details;
}
