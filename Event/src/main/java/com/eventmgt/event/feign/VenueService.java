package com.eventmgt.event.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eventmgt.event.model.Venue;

@FeignClient(name = "Venue",url = "http://localhost:8093/venue/" )
public interface VenueService {

	@GetMapping("name/{name}")
	List<Venue> getVenue(@PathVariable("name") String venue);
}
