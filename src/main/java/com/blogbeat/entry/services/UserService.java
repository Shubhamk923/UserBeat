package com.blogbeat.entry.services;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
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

import com.blogbeat.entry.entities.User;
import com.blogbeat.entry.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	PasswordEncoder passwordEncoder;

//	LinkedHashMap<Integer, User> users = new LinkedHashMap<>();
//	AtomicInteger atomicInteger = new AtomicInteger();
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	
//	public UserService() {
//		User user = new User(atomicInteger.incrementAndGet(),"Shubham","Shubham@gmail.com","abc123");
//		User user2 = new User(atomicInteger.incrementAndGet(),"Rohit","Rohit@gmail.com","abc123");
//		users.put(user.getId(), user);
//		users.put(user2.getId(), user2);
//	}
	
	@CacheEvict(value = "users" , allEntries = true)
	public User create(User user){
//		user.setId(atomicInteger.incrementAndGet());
//		users.put(user.getId() , user);
//		System.out.println(users);
//		return user;
		if(this.userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user with email, id are already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));	
		
		String userEmail = user.getEmail();
		String subject = "Welcome to our application";
		String body = "Dear "+ user.getName()+",\n\nWelcome To Our BlogBeat Application";
		emailService.sendEmail(userEmail, subject, body);
		return this.userRepository.save(user);
	}
	
	
	public User createAdmin(User user){
		if(this.userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user with email id are already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("ADMIN"));
		return this.userRepository.save(user);
	}
	
	
	@Cacheable("users")
	public List<User> list(int page , int size , String sortby){
//		User user = new User(atomicInteger.incrementAndGet(),"Shubham","Shubham@gmail.com","abc123");
//		User user2 = new User(atomicInteger.incrementAndGet(),"Rohit","Rohit@gmail.com","abc123");
//		users.put(user.getId(), user);
//		users.put(user2.getId(), user2);
//		return users.values();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Pageable pageable = PageRequest.of(page , size ,Sort.by(sortby));
		Page<User> pagedata = this.userRepository.findAll(pageable);
		return pagedata.getContent();
	}
	
	
	@Scheduled(fixedRate = 30000)//30 seconds
	@CacheEvict(value = "users" , allEntries = true)
	public void evictAllCache() {
		System.out.println("Cached evected every 30 seconds");
	}
	
	public User getUserId(int id) {
//		User user =  this.users.get(id);
//		if(user == null) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user with id "+id+" not found");
//			
//		}
//		return user;
		return userRepository.findById(id)
				.orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"user with id "+id+" not found")
						);
				
	}

	public void deleteUserById(int id) {
		if(!userRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user with id "+id+" not found");
		}
//		getUserId(id);
//		this.users.remove(id);
		userRepository.deleteById(id);
		
	}

	public User updateUser(int id, User user) {
		if(!userRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user with id "+id+" not found");
		}
		user.setId(id);
//		this.users.put(user.getId(), user);
//		return user;
		return userRepository.save(user);
	}
	
	public User getUserByEmail(String email) {
		 User user = this.userRepository.findByEmail(email).orElseThrow(()->
		new ResponseStatusException(HttpStatus.NOT_FOUND,"user with email "+email+" not found")
				);
		return user; 
	}
	
	public List<User> getUserByName(String name){
		return this.userRepository.findByName(name);
	}
	
	

	
	
	
	
	
	
	
	
	
}
