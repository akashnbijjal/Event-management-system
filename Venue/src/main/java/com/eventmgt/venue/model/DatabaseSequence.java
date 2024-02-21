package com.eventmgt.venue.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Document(collection = "database_sequences")
public class DatabaseSequence {

	@Id
	private String id;

	private long seq;
}
