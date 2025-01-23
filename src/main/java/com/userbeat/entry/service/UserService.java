package com.userbeat.entry.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userbeat.entry.entities.User;
import com.userbeat.entry.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	
	

	public User create(User user) {
		
		if(this.userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user with email, id are already exists");
		}
		
		String userEmail = user.getEmail();
		String subject = "Welcome to our application";
		String body = "Dear "+ user.getName()+",\n\nWelcome To Our BlogBeat Application";
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
		return this.userRepository.save(user);
	}
	
	public User createAdmin(User user) {
		if(this.userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user with email, id are already exists");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("ADMIN"));
		return this.userRepository.save(user);
	}
	
	
	
	@Cacheable("users")
	public List<User> getAllUser(int page , int size , String sortby){
//		return this.userRepository.findAll();
		
		try {
			Thread.sleep(3000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Pageable pageable = PageRequest.of(page, size , Sort.by(sortby));
		Page<User> pagedata = this.userRepository.findAll(pageable);
		return pagedata.getContent();
	}

	public User getById(int id) {
		
		return userRepository.findById(id)
				.orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"user with id "+id+" not found")
						);
	}

	public User updatedById(int id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}

	public void deleteById(int id) {
		
		this.userRepository.deleteById(id);
		
	}

	public User getByGmail(String email) {
		
		User user = userRepository.findByEmail(email).orElseThrow(()->
		new ResponseStatusException(HttpStatus.NOT_FOUND,"user with gamil "+email+" not found")
				);
		return user;
	}

	public List<User> getByName(String name) {
		
		return userRepository.findByName(name);
	}

	
	 
	
	
	
	
	
}
