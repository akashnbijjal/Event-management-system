package com.eventmgt.event.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.eventmgt.event.exception.EventAlreadyExists;
import com.eventmgt.event.exception.EventNotFound;
import com.eventmgt.event.feign.VenueService;
import com.eventmgt.event.model.Event;
import com.eventmgt.event.repository.EventRepository;

@SpringBootTest
class EventServiceImplTest {

	@MockBean
	private EventRepository eventRepository;

	@Autowired
	private EventServiceImpl service;

	@Autowired
	private VenueService venueClient;

	@MockBean
	private SequenceGeneratorService sequenceGeneratorService;

	@Test
	void test_createEvent_success() {
		Map<String, Double> ticketTypesPrices = new HashMap<>();
		ticketTypesPrices.put("VIP", 100.0);
		ticketTypesPrices.put("General", 50.0);
		Event newEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));

		Event expectedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Mockito.when(!eventRepository.existsByStartDateTimeAndEndDateTime(new Date(), new Date())).thenReturn(true);
		Mockito.when(sequenceGeneratorService.generateSequence(Event.SEQUENCE_NAME)).thenReturn(1L);
		Mockito.when(eventRepository.save(newEvent)).thenReturn(expectedEvent);
		Event savedEvent = service.createEvent(newEvent);
		assertEquals(expectedEvent, savedEvent);
	}

	@Test
	void test_createEvent_failed() {
		Map<String, Double> ticketTypesPrices = new HashMap<>();
		ticketTypesPrices.put("VIP", 100.0);
		ticketTypesPrices.put("ordinary", 2000.0);
		Event newEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", ticketTypesPrices, venueClient.getVenue("UB_City_Bengaluru"));
		Mockito.when(!eventRepository.existsByStartDateTimeAndEndDateTime(new Date(), new Date())).thenReturn(false);
		EventAlreadyExists exception = assertThrows(EventAlreadyExists.class, () -> service.createEvent(newEvent));
		assertEquals("Venue is already booked during this time", exception.getMsg());
	}

	@Test
	void test_geteventbyid_success() {
		long eventId = 1;
		Event expectedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Mockito.when(eventRepository.existsById(eventId)).thenReturn(true);
		Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.of(expectedEvent));
		Event actualEvent = service.geteventbyid(eventId);
		assertEquals(expectedEvent, actualEvent);

	}

	@Test
	void test_geteventbyid_failed() {
		long eventId = -1;
		Mockito.when(eventRepository.existsById(eventId)).thenReturn(false);
		EventNotFound exception = assertThrows(EventNotFound.class, () -> service.geteventbyid(eventId));
		assertEquals("Event not found with ID: " + eventId, exception.getMsg());
	}

	@Test
	void test_geteventbyname_success() {
		String eventname = "birthday";
		Event expectedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Mockito.when(eventRepository.findByEventnameIgnoreCaseContaining(eventname)).thenReturn(List.of(expectedEvent));
		List<Event> actualEvents = service.geteventbyname(eventname);
		assertEquals(List.of(expectedEvent), actualEvents);
	}

	@Test
	void test_listofevents() {
		Date startDateTime = new Date();
		Date endDateTime = new Date();
		Event expectedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 100, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Mockito.when(eventRepository.findByStartDateTimeAndEndDateTime(startDateTime, endDateTime))
				.thenReturn(List.of(expectedEvent));
		List<Event> list = service.listofevents(startDateTime, endDateTime);
		assertEquals(List.of(expectedEvent), list);

	}

	@Test
	void test_updateevent() {
		Event updatedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 200, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));

		Event expectedEvent = new Event(1, "birthday", "UB_City_Bengaluru", "This is a birthday event", new Date(),
				new Date(), 200, "Vijay Malya", Collections.singletonMap("VIP", 100.00),
				venueClient.getVenue("UB_City_Bengaluru"));
		Mockito.when(eventRepository.save(updatedEvent)).thenReturn(expectedEvent);
		Event savedEvent = service.updateevent(updatedEvent);
		assertEquals(expectedEvent, savedEvent);
	}

	@Test
	void test_deleteevent_success() {
		long eventId = 1;
		Mockito.when(eventRepository.existsById(eventId)).thenReturn(true);
		String result = service.deleteevent(eventId);

		assertEquals("Event deleted successfully", result);
	}

	@Test
	void test_deleteevent_failed() {
		long eventId = -1;
		Mockito.when(eventRepository.existsById(eventId)).thenReturn(false);
		String result = service.deleteevent(eventId);
		assertEquals("Event not found with ID:" + eventId, result);
	}

}
