package com.eventmgt.event.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmgt.event.model.Event;
import com.eventmgt.event.service.EventServiceImpl;

@RestController
@RequestMapping("event")
public class EventController {

	@Autowired
	private EventServiceImpl service;

	@PostMapping("createevent")
	public ResponseEntity<Event> createEvent(@RequestBody Event event) {
		Event event2 = service.createEvent(event);
		return new ResponseEntity<>(event2, HttpStatus.CREATED);
	}

	@GetMapping("get/{eventId}")
	public ResponseEntity<Event> geteventbyeventid(@PathVariable("eventId") long eventId) {
		Event event = service.geteventbyid(eventId);
		return new ResponseEntity<Event>(event, HttpStatus.ACCEPTED);
	}

	@GetMapping("eventname/{eventname}")
	public ResponseEntity<List<Event>> geteventbyeventname(@PathVariable("eventname") String eventname) {
		List<Event> list = service.geteventbyname(eventname);
		return new ResponseEntity<List<Event>>(list, HttpStatus.ACCEPTED);
	}

	@PutMapping("updateevent")
	public ResponseEntity<Event> updateevent(@RequestBody Event event) {
		Event event2 = service.updateevent(event);
		return new ResponseEntity<Event>(event2, HttpStatus.CREATED);
	}

	@DeleteMapping("delete/{eventId}")
	public ResponseEntity<String> deleteevent(@PathVariable("eventId") long eventId) {
		String event = service.deleteevent(eventId);
		return new ResponseEntity<String>(event, HttpStatus.ACCEPTED);
	}

	@GetMapping("date/{startDateTime}/{endDateTime}")
	public ResponseEntity<List<Event>> searchbyeventsbydate(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDateTime,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDateTime) {
		List<Event> list = service.listofevents(startDateTime, endDateTime);
		return new ResponseEntity<List<Event>>(list, HttpStatus.ACCEPTED);
	}

}
