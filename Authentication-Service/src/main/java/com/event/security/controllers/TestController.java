package com.event.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.security.models.User;
import com.event.security.repository.UserRepository;
import com.event.security.security.services.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	private UserRepository repo;

	@Autowired
	private UserDetailsServiceImpl service;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ATTENDEE') or hasRole('ORGANIZER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/org")
	@PreAuthorize("hasRole('ORGANIZER')")
	public String moderatorAccess() {
		return "Attendee Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	@GetMapping("admin/allusers")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> listofusers() {
		return repo.findAll();
	}

	@DeleteMapping("admin/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteUser(@PathVariable("username") String username) {
		service.deleteUser(username);
	}
}
