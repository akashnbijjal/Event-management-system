package com.eventmgt.venue.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.eventmgt.venue.model.DatabaseSequence;

@Service
public class SequenceGeneratorService {
	private MongoOperations mongoOperations;

	@Autowired
	public SequenceGeneratorService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	public long generateSequence(String seqName) {
		Query query = new Query(Criteria.where("_id").is(seqName));

		Update update = new Update().inc("seq", 1);

		DatabaseSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				DatabaseSequence.class);

		return (counter != null) ? counter.getSeq() : 1;
	}
}