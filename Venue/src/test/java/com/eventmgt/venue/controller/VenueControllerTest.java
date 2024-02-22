package com.eventmgt.venue.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.eventmgt.venue.model.Venue;
import com.eventmgt.venue.service.VenueServiceImpl;

@SpringBootTest
class VenueControllerTest {

	@MockBean
	private VenueServiceImpl service;

	@Autowired
	private VenueController controller;

	@Test
	void test_addvenue() {
		Venue newVenue = new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500, "Wi-Fi, Projectors, Catering, Liquor",
				1500000, "ub@venue.com", "+91834567890");
		Venue expectedVenue = new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500, "Wi-Fi, Projectors, Catering, Liquor",
				1500000, "ub@venue.com", "+91834567890");
		Mockito.when(service.addvenue(newVenue)).thenReturn(expectedVenue);
		ResponseEntity<Venue> response = controller.addvenue(newVenue);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(expectedVenue, response.getBody());
	}

	@Test
	public void testListOfVenueOnAddress_success() {

		String address = "UB_City_Bengaluru";
		List<Venue> expectedVenues = List.of(new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500,
				"Wi-Fi, Projectors, Catering, Liquor", 1500000, "ub@venue.com", "+91834567890"));
		Mockito.when(service.listofvenue(address)).thenReturn(expectedVenues);

		ResponseEntity<List<Venue>> response = controller.listofvenueonaddress(address);

		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(expectedVenues, response.getBody());
		verify(service).listofvenue(address);
	}

	@Test
	public void testSearchVenueByName_success() {

		String name = "UB_City_Bengaluru";
		List<Venue> expectedVenues = List.of(new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500,
				"Wi-Fi, Projectors, Catering, Liquor", 1500000, "ub@venue.com", "+91834567890"));
		Mockito.when(service.getvenuebyname(name)).thenReturn(expectedVenues);

		ResponseEntity<List<Venue>> response = controller.searchvenuebyname(name);

		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(expectedVenues, response.getBody());
		verify(service).getvenuebyname(name);
	}

	@Test
	public void testGetVenueById_success() {
		long venueId = 1;
		Venue expectedVenue = new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500, "Wi-Fi, Projectors, Catering, Liquor",
				1500000, "ub@venue.com", "+91834567890");
		Mockito.when(service.getvenuebyid(venueId)).thenReturn(expectedVenue);
		ResponseEntity<Venue> response = controller.getvenuebyid(venueId);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(expectedVenue, response.getBody());
		verify(service).getvenuebyid(venueId);
	}

	@Test
	public void testupdatevenue() {
		Venue newVenue = new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500, "Wi-Fi, Projectors, Catering, Liquor",
				1500000, "ub@venue.com", "+91834567890");
		Venue expectedVenue = new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500, "Wi-Fi, Projectors, Catering, Liquor",
				1500000, "ub@venue.com", "+91834567890");

		Mockito.when(service.updatevenue(newVenue)).thenReturn(expectedVenue);
		ResponseEntity<Venue> response = controller.updatevenue(newVenue);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(expectedVenue, response.getBody());
		verify(service).updatevenue(newVenue);
	}

}
