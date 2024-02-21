package com.eventmgt.event.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventmgt.event.exception.EventAlreadyExists;
import com.eventmgt.event.exception.EventNotFound;
import com.eventmgt.event.feign.VenueService;
import com.eventmgt.event.model.DatabaseSequence;
import com.eventmgt.event.model.Event;
import com.eventmgt.event.model.Venue;
import com.eventmgt.event.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository repo;

	@Autowired
	private VenueService venueservice;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

	@Override
	public Event createEvent(Event createEvent) {
		if (!repo.existsByStartDateTimeAndEndDateTime(createEvent.getStartDateTime(), createEvent.getEndDateTime())) {
			createEvent.setEventId(sequenceGeneratorService.generateSequence(Event.SEQUENCE_NAME));
			createEvent.setVenuedetails(venueservice.getVenue(createEvent.getVenue()));
			Event event = repo.save(createEvent);

			logger.info("Event created successfully!!!!!!");
			return event;
		} else {

			throw new EventAlreadyExists("Venue is already booked during this time");
		}
	}

	@Override
	public Event geteventbyid(long eventId) {
		if (repo.existsById(eventId)) {
			Event event = repo.findById(eventId).get();
			List<Venue> list = venueservice.getVenue(event.getVenue());
			event.setVenuedetails(list);
			logger.info("Retrieved event with ID: {}", eventId);
			return event;
		} else {
			throw new EventNotFound("Event not found with ID: " + eventId);
		}

	}

	@Override
	public List<Event> geteventbyname(String eventname) {
		List<Event> list = repo.findByEventnameIgnoreCaseContaining(eventname);

		if (list.isEmpty()) {
			logger.warn("\"No events found with name containing: {}", eventname);
		} else {
			logger.info("Found {} event(s) with name containing: {}", list.size(), eventname);
		}
		for (Event event : list) {
			event.setVenuedetails(venueservice.getVenue(event.getVenue()));
		}
		return list;
	}

	@Override
	public List<Event> listofevents(Date startDateTime, Date endDateTime) {
		List<Event> list = repo.findByStartDateTimeAndEndDateTime(startDateTime, endDateTime);
		if (list.isEmpty()) {
			logger.info("\"No events found between these dates: {}");
		} else {
			logger.info("Found {} event(s) within the dates: {}");
		}

		return list;
	}

	@Override
	public Event updateevent(Event updateEvent) {
		updateEvent.setVenuedetails(venueservice.getVenue(updateEvent.getVenue()));
		Event event = repo.save(updateEvent);

		logger.info("Event details updated!!!!!!!!");
		return event;
	}

	@Override
	public String deleteevent(long eventId) {
		if (repo.existsById(eventId)) {
			repo.deleteById(eventId);
			logger.info("Event deleted successfully");
			return "Event deleted successfully";
		} else {
			logger.warn("Not Found event with ID: {}" + eventId);
			return "Event not found with ID:" + eventId;
		}

	}

}
