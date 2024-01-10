package com.eventmgt.ticket.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eventmgt.ticket.model.Event;

@FeignClient(name = "Event", url = "http://localhost:8091/event/")
public interface EventClient {

	@GetMapping("get/{eventId}")
	Event getEvent(@PathVariable("eventId") long eventId);

}
