package com.eventmgt.event.service;

import java.util.Date;
import java.util.List;

import com.eventmgt.event.model.Event;

public interface EventService {

	Event createEvent(Event createEvent);

	Event geteventbyid(long eventId);

	List<Event> geteventbyname(String eventname);

	List<Event> listofevents(Date startDateTime, Date endDateTime);

	Event updateevent(Event updateEvent);

	String deleteevent(long eventId);

}
