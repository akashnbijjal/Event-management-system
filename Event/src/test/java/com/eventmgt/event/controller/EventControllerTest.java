package com.eventmgt.event.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eventmgt.event.feign.VenueService;
import com.eventmgt.event.model.Event;
import com.eventmgt.event.service.EventServiceImpl;

@SpringBootTest
class EventControllerTest {

	@MockBean
	private EventServiceImpl service;

	@Autowired
	private EventController controller;

	@Autowired
	private VenueService venueClient;

	@Test
	void test_createEvent() {
		Event newEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Event expectedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));

		Mockito.when(service.createEvent(newEvent)).thenReturn(expectedEvent);
		ResponseEntity<Event> response = controller.createEvent(newEvent);
		System.out.println(response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(expectedEvent, response.getBody());
	}

	@Test
	void test_geteventbyeventid() {
		long eventId = 1;
		Event expectedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Mockito.when(service.geteventbyid(eventId)).thenReturn(expectedEvent);
		ResponseEntity<Event> response = controller.geteventbyeventid(eventId);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(expectedEvent, response.getBody());
	}

	@Test
	void test_geteventbyeventname() {
		String eventname = "birthday";
		Event expectedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Mockito.when(service.geteventbyname(eventname)).thenReturn(List.of(expectedEvent));
		ResponseEntity<List<Event>> response = controller.geteventbyeventname(eventname);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(List.of(expectedEvent), response.getBody());
	}

	@Test
	void test_updateevent() {
		Event updateEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Event expectedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Mockito.when(service.createEvent(updateEvent)).thenReturn(expectedEvent);
		ResponseEntity<Event> response = controller.createEvent(updateEvent);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(expectedEvent, response.getBody());
	}

	@Test
	void test_deleteevent() {
		long eventId = 1;

		String message = "Event deleted successfully";
		Mockito.when(service.deleteevent(eventId)).thenReturn(message);
		ResponseEntity<String> response = controller.deleteevent(eventId);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(message, response.getBody());
	}

	@Test
	void test_searchbyeventsbydate() {
		Date startDateTime = new Date();
		Date endDateTime = new Date();
		Event expectedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Mockito.when(service.listofevents(startDateTime, endDateTime)).thenReturn(List.of(expectedEvent));
		ResponseEntity<List<Event>> response = controller.searchbyeventsbydate(startDateTime, endDateTime);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(List.of(expectedEvent), response.getBody());
	}
}
