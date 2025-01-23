package com.userbeat.entry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userbeat.entry.entities.User;
import com.userbeat.entry.service.UserService;
import com.userbeat.entry.service.WheatherService;
import com.userbeat.entryapiresponse.WheaterResponse;

import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/public")
public class PublicContoller {

	@Autowired
	private UserService userservice;
	
	@Autowired
	private WheatherService wheatherService;
	

	@GetMapping("/healthCheck")
	
	public String healthCheck() {
		return "ok";
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<User>createUser(@RequestBody @Validated User user) {
		return new ResponseEntity<>(this.userservice.create(user),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> greeting(){
//		User user = userservice.getByName();
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		WheaterResponse wheaterResponse = wheatherService.getWeather("Mumbai");
        String greeting = "";
        if (wheaterResponse != null) {
            greeting = ", Weather feels like " + wheaterResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
			
	}
	
	
	
	
	
	
	
	
	
	
	
}
