package com.eventmgt.event.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String msg;
}
