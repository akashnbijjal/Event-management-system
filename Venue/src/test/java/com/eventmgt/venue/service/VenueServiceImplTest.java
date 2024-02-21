package com.eventmgt.venue.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eventmgt.venue.exception.VenueNotFoundException;
import com.eventmgt.venue.model.Venue;
import com.eventmgt.venue.repository.venuerepository;

@SpringBootTest
class VenueServiceImplTest {

	@MockBean
	private venuerepository venuerepository;

	@Autowired
	private VenueServiceImpl service;

	@Test
	void test_addvenue_success() {
		Venue newVenue = new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500, "Wi-Fi, Projectors, Catering, Liquor",
				1500000, "ub@venue.com", "+91834567890");
		Venue expectedVenue = new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500, "Wi-Fi, Projectors, Catering, Liquor",
				1500000, "ub@venue.com", "+91834567890");
		Mockito.when(venuerepository.save(newVenue)).thenReturn(expectedVenue);
		Venue savedVenue = service.addvenue(newVenue);
		assertEquals(expectedVenue, savedVenue);

	}

	@Test
	public void testListOfVenue_success() {

		String address = "Main Street";
		List<Venue> expectedVenues = List.of(new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500,
				"Wi-Fi, Projectors, Catering, Liquor", 1500000, "ub@venue.com", "+91834567890"));
		Mockito.when(venuerepository.findByAddressIgnoreCaseContaining(address)).thenReturn(expectedVenues);

		List<Venue> actualVenues = service.listofvenue(address);

		assertEquals(expectedVenues, actualVenues);
		verify(venuerepository).findByAddressIgnoreCaseContaining(address);

	}

	@Test
	public void testListOfVenue_noMatchingVenues() {

		String address = "Unknown Address";
		Mockito.when(venuerepository.findByAddressIgnoreCaseContaining(address)).thenReturn(Collections.emptyList());

		List<Venue> actualVenues = service.listofvenue(address);

		assertTrue(actualVenues.isEmpty());
		verify(venuerepository).findByAddressIgnoreCaseContaining(address);

	}

	@Test
	public void testGetVenueByName_success() {

		String name = "UB_City_Bengaluru";
		List<Venue> expectedVenues = List.of(new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500,
				"Wi-Fi, Projectors, Catering, Liquor", 1500000, "ub@venue.com", "+91834567890"));
		Mockito.when(venuerepository.findByNameIgnoreCaseContaining(name)).thenReturn(expectedVenues);

		List<Venue> actualVenues = service.getvenuebyname(name);
		assertEquals(expectedVenues, actualVenues);

	}

	@Test
	public void testGetVenueById_success() {

		long venueId = 1;
		Venue expectedVenue = new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500, "Wi-Fi, Projectors, Catering, Liquor",
				1500000, "ub@venue.com", "+91834567890");
		Mockito.when(venuerepository.findById(venueId)).thenReturn(Optional.of(expectedVenue));

		Venue actualVenue = service.getvenuebyid(venueId);

		assertEquals(expectedVenue, actualVenue);
		verify(venuerepository).findById(venueId);

	}

	@Test
	public void testGetVenueById_notFound() {

		long venueId = 200;
		Mockito.when(venuerepository.findById(venueId)).thenReturn(Optional.empty());

		VenueNotFoundException exception = assertThrows(VenueNotFoundException.class,
				() -> service.getvenuebyid(venueId));
		verify(venuerepository).findById(venueId);
		assertEquals("Venue not found with ID: " + venueId, exception.getMsg());
	}

	@Test
	public void testUpdateVenue_success() {

		Venue updateVenue = new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500, "Wi-Fi, Projectors, Catering, Liquor",
				1500000, "ub@venue.com", "+91834567890");
		Venue expectedVenue = new Venue(1, "UB_City_Bengaluru", "Bengaluru", 500, "Wi-Fi, Projectors, Catering, Liquor",
				1500000, "ub@venue.com", "+91834567890");
		Mockito.when(venuerepository.save(updateVenue)).thenReturn(expectedVenue);

		Venue actualVenue = service.updatevenue(updateVenue);

		assertEquals(expectedVenue, actualVenue);
		verify(venuerepository).save(updateVenue);

	}

}
