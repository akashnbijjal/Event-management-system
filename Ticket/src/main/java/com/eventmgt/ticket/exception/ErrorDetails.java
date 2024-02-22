package com.eventmgt.ticket.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class ErrorDetails {

	private LocalDateTime timestamp;

	private String message;

	private String details;
}
