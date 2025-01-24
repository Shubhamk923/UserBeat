package com.blogbeat.entry.services;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.blogbeat.entry.entities.User;
import com.blogbeat.entry.repositories.UserRepository;

import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
//@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
	
//	To log information and debugging messages during execution.
	public static final org.slf4j.Logger Logger = org.slf4j.LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Logger.info("Loading user by email: {}",email);
//		log.info("hahaha");
		User user = userRepository.findByEmail(email) //Attempts to fetch a User entity from the database using the email
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
				
				Logger.info("Loading user by email: {}",user);
				
				//covert role to simpleGrantedAuthority
				List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+role))
				.collect(Collectors.toList());
				
				return org.springframework.security.core.userdetails.User.builder()
						.username(user.getEmail())
						.password(user.getPassword())
//						.authorities(user.getRoles().stream()
//						.map(SimpleGrantedAuthority::new)
//						.collect(Collectors.toList()))
						.authorities(authorities)
                        .build();
	}

	

}
