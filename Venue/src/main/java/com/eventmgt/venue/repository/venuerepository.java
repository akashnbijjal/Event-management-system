package com.eventmgt.venue.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eventmgt.venue.model.Venue;

public interface venuerepository extends MongoRepository<Venue, Long> {

	List<Venue> findByAddressIgnoreCaseContaining(String address);

	List<Venue> findByNameIgnoreCaseContaining(String name);

}
