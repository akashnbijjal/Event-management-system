package com.eventmgt.venue.service;

import java.util.List;

import com.eventmgt.venue.model.Venue;

public interface VenueService {

	Venue addvenue(Venue newvenue);

	List<Venue> listofvenue(String address);

	List<Venue> getvenuebyname(String name);

	Venue getvenuebyid(long venueId);

}
