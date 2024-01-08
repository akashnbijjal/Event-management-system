package com.eventmgt.event.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eventmgt.event.model.Event;

public interface EventRepository extends MongoRepository<Event, Long> {

	List<Event> findByEventnameIgnoreCaseContaining(String eventname);

	boolean existsByStartDateTimeAndEndDateTime(Date startDateTime, Date endDateTime);

	List<Event> findByStartDateTimeAndEndDateTime(Date startDateTime, Date endDateTime);

}
