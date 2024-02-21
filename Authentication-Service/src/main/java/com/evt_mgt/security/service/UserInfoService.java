package com.evt_mgt.security.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.evt_mgt.security.model.Userinfo;
import com.evt_mgt.security.repository.UserRepository;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	private UserRepository userInfoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Userinfo> userInfo = userInfoRepository.findByName(username);
		return userInfo.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
	}

	public Userinfo addUser(Userinfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfo.setUserId(sequenceGeneratorService.generateSequence(Userinfo.SEQUENCE_NAME));
		return userInfoRepository.save(userInfo);
	}

	public List<Userinfo> getAllUser() {
		return userInfoRepository.findAll();
	}

	public Userinfo getUser(long userId) {
		return userInfoRepository.findById(userId).get();
	}
}