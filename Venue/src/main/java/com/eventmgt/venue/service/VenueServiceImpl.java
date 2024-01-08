package com.eventmgt.venue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eventmgt.venue.model.Venue;
import com.eventmgt.venue.repository.venuerepository;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private venuerepository repo;

	@Override
	public Venue addvenue(Venue newvenue) {
		Venue venue = repo.save(newvenue);
		return venue;
	}

	@Override
	public List<Venue> listofvenue(String address) {
		List<Venue> list = repo.findByAddressIgnoreCaseContaining(address);
		return list;
	}

	@Override
	public List<Venue> getvenuebyname(String name) {
		List<Venue> venue = repo.findByNameIgnoreCaseContaining(name);
		return venue;
	}

	@Override
	public Venue getvenuebyid(long venueId) {
		Venue venue = repo.findById(venueId).get();
		return venue;
	}

}
