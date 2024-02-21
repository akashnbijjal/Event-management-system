package com.eventmgt.venue.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventmgt.venue.exception.VenueNotFoundException;
import com.eventmgt.venue.model.Venue;

import com.eventmgt.venue.repository.venuerepository;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	private static final Logger logger = LogManager.getLogger(VenueServiceImpl.class);
	@Autowired
	private venuerepository repo;

	@Override
	public Venue addvenue(Venue newvenue) {
		logger.info("Adding new venue: {}", newvenue);
		newvenue.setVenueId(sequenceGeneratorService.generateSequence(Venue.SEQUENCE_NAME));
		Venue savedVenue = repo.save(newvenue);
		logger.info("Venue added successfully: {}", savedVenue);
		return savedVenue;
	}

	@Override
	public List<Venue> listofvenue(String address) {
		logger.info("Retrieving venues by address: {}", address);
		List<Venue> venues = repo.findByAddressIgnoreCaseContaining(address);
		logger.info("Found {} venues", venues.size());
		return venues;
	}

	@Override
	public List<Venue> getvenuebyname(String name) {
		logger.info("Retrieving venues by name: {}", name);
		List<Venue> venues = repo.findByNameIgnoreCaseContaining(name);
		logger.info("Found {} venues", venues.size());
		return venues;
	}

	@Override
	public Venue getvenuebyid(long venueId) {
		logger.info("Retrieving venue by ID: {}", venueId);
		Venue venue = repo.findById(venueId).orElseThrow(() -> {
			logger.warn("Venue not found with ID: {}", venueId);
			return new VenueNotFoundException("Venue not found with ID: " + venueId);
		});
		logger.info("Venue retrieved: {}", venue);
		return venue;
	}

	@Override
	public Venue updatevenue(Venue updatevenue) {
		logger.info("Updating venue: {}", updatevenue);
		Venue updatedVenue = repo.save(updatevenue);
		logger.info("Venue updated successfully: {}", updatedVenue);
		return updatedVenue;
	}

}
