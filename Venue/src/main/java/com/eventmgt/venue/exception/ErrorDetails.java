package com.eventmgt.venue.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDetails {

	private LocalDateTime timestamp;

	private String message;

	private String details;
}
