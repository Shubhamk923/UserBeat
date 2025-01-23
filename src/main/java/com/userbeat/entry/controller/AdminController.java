package com.userbeat.entry.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userbeat.entry.entities.Blog;
import com.userbeat.entry.entities.User;
import com.userbeat.entry.service.BlogService;
import com.userbeat.entry.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/admin/users")
public class AdminController {
	
	@Autowired
	UserService userService;

	@PostMapping("/createadmin")
	public ResponseEntity<User>createUser(@RequestBody @Validated User user) {
		return new ResponseEntity<>(this.userService.createAdmin(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAlluser")
	public ResponseEntity<List<User>> getMethodName(
			@RequestParam(defaultValue = "0" , required = false) int page,
			@RequestParam(defaultValue = "5" , required = false) int size,
			@RequestParam(defaultValue = "name" , required = false) String sortby
			){
		return new ResponseEntity<>(this.userService.getAllUser((page>0)?page-1:page,size,sortby),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable int id){
		User user = userService.getById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateById(@PathVariable int id , @RequestBody @Validated User user){
		User users = userService.updatedById(id , user);
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable int id){
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<User> getByGmail(@PathVariable String email){
		
		User user = userService.getByGmail(email);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<User>> getByName(@PathVariable String name){
		
		List<User> user = userService.getByName(name);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	
	
	

	
	
	
	
	
	
}
