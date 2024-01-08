package com.eventmgt.ticket.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ticketNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String msg;
}
