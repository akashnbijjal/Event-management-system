package com.eventmgt.venue.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eventmgt.venue.model.Venue;
import com.eventmgt.venue.service.VenueServiceImpl;

@RestController
@RequestMapping("venue")
public class VenueController {

	@Autowired
	public VenueServiceImpl service;

	@PostMapping("addvenue")
	public ResponseEntity<Venue> addvenue(@RequestBody Venue venue) {
		Venue venue2 = service.addvenue(venue);
		return new ResponseEntity<Venue>(venue2, HttpStatus.CREATED);
	}

	@GetMapping("location/{address}")
	public ResponseEntity<List<Venue>> listofvenueonaddress(@PathVariable("address") String address) {
		List<Venue> list = service.listofvenue(address);
		return new ResponseEntity<List<Venue>>(list, HttpStatus.ACCEPTED);
	}

	@GetMapping("name/{name}")
	public ResponseEntity<List<Venue>> searchvenuebyname(@PathVariable("name") String name) {
		List<Venue> venue = service.getvenuebyname(name);
		return new ResponseEntity<List<Venue>>(venue, HttpStatus.ACCEPTED);
	}

	@GetMapping("id/{venueId}")
	public ResponseEntity<Venue> getvenuebyid(@PathVariable("venueId") long venueId) {
		Venue venue = service.getvenuebyid(venueId);
		return new ResponseEntity<Venue>(venue, HttpStatus.ACCEPTED);
	}

}
