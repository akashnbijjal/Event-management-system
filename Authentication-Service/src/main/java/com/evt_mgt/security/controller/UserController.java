package com.evt_mgt.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.evt_mgt.security.model.AuthRequest;
import com.evt_mgt.security.model.Userinfo;
import com.evt_mgt.security.service.JwtService;
import com.evt_mgt.security.service.UserInfoService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Hotel-Rating application";
	}

	@PostMapping("/signup")
	public Userinfo addUser(@Valid @RequestBody Userinfo userInfo) {
		return userInfoService.addUser(userInfo);

	}

	@PostMapping("/signin")
	public String addUser(@Valid @RequestBody AuthRequest authRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		if (authenticate.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUserName());
		} else {
			throw new UsernameNotFoundException("Invalid user request");
		}
	}

	@GetMapping("/getUsers")
	@PreAuthorize("hasAuthority('ADMIN_ROLES')")
	public List<Userinfo> getAllUsers() {
		return userInfoService.getAllUser();
	}

	@GetMapping("/getUsers/{id}")
	@PreAuthorize("hasAuthority('USER_ROLES')")
	public Userinfo getAllUsers(@PathVariable long id) {
		return userInfoService.getUser(id);
	}

}
