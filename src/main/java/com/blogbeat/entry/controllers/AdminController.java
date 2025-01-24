package com.blogbeat.entry.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogbeat.entry.entities.Blog;
import com.blogbeat.entry.entities.User;
import com.blogbeat.entry.repositories.BlogRepo;
import com.blogbeat.entry.services.BlogService;
import com.blogbeat.entry.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("admin/users")
public class AdminController {

	@Autowired
	UserService userservice;
	
	@Autowired
	BlogService blogService;
	
	@GetMapping
	@Tag(name = "get" , description = "Get All Users Data Including Pagination And Sorting")
	public ResponseEntity<List<User>>getAllUsers(
			@RequestParam(defaultValue = "0" , required = false) int page,
			@RequestParam(defaultValue = "5" , required = false) int size,
			@RequestParam(defaultValue = "name" , required = false) String sortby
			){
		List<User> users = userservice.list((page>0)?page-1:page,size,sortby);
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@PostMapping("/createadmin")
	public ResponseEntity<User>createUser(@RequestBody @Validated User user) {
		return new ResponseEntity<>(this.userservice.createAdmin(user),HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		User user = userservice.getUserId(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  deleteUserById(@PathVariable int id) {
		 userservice.deleteUserById(id);
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User>  updateUserById(@PathVariable int id , @RequestBody @Validated User user) {
		User updateuser = userservice.updateUser(id, user);
		return new ResponseEntity<>(updateuser,HttpStatus.OK);
	}
	
	
	@GetMapping("/email/{email}")
	public ResponseEntity<User>  findUserByEmail(@PathVariable String email) {
		User user = userservice.getUserByEmail(email);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<User>>  findUsersByName(@PathVariable String name) {
		List<User> users = userservice.getUserByName(name);
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
