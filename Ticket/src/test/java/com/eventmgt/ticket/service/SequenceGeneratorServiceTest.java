package com.eventmgt.ticket.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import com.eventmgt.ticket.model.DatabaseSequence;

@ExtendWith(MockitoExtension.class)
class SequenceGeneratorServiceTest {

	@Mock
	private MongoOperations mongoOperations;

	@InjectMocks
	private SequenceGeneratorService sequenceGeneratorService;

	@Test
	void testGenerateSequence() {

		String seqName = "testSeq";
		DatabaseSequence databaseSequence = new DatabaseSequence(seqName, 1);

		when(mongoOperations.findAndModify(any(), any(Update.class), any(), eq(DatabaseSequence.class)))
				.thenReturn(databaseSequence);

		long generatedSequence = sequenceGeneratorService.generateSequence(seqName);

		assertEquals(1, generatedSequence);
	}
}
