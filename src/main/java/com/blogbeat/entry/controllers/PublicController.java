package com.blogbeat.entry.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogbeat.entry.entities.User;
import com.blogbeat.entry.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	UserService userservice;

	@GetMapping("/healthCheck")
	
	public String healthCheck() {
		return "ok";
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<User>createUser(@RequestBody @Validated User user) {
		return new ResponseEntity<>(this.userservice.create(user),HttpStatus.CREATED);
	}

	
	
	
}
