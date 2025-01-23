package com.userbeat.entry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userbeat.entry.entities.Blog;
import com.userbeat.entry.service.BlogService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user/blogs")
public class BlogController {
	
	@Autowired
	BlogService blogService;

	@PostMapping
	public ResponseEntity<Blog> create(@RequestBody @Validated Blog blog ){
		String email = getCurrentUserEmail();
		Blog blogs = blogService.create(blog , email);
		return new ResponseEntity<>(blogs,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Blog>> getAllBlogs(){
		
		String email = getCurrentUserEmail();
		List<Blog> blogs = blogService.getAllBlogs(email);
		return new ResponseEntity<>(blogs,HttpStatus.OK);
	}
	
	@GetMapping("/{blogId}")
	public ResponseEntity<Blog> getUserBlog(@PathVariable int blogId){
		
		String email = getCurrentUserEmail();
		Blog userBlog = blogService.getUserBlog(email,blogId);
		return new ResponseEntity<>(userBlog,HttpStatus.OK);
	}
	
	@PutMapping("/{blogId}")
    public ResponseEntity<Blog> updateUserBlog(@PathVariable @Validated int blogId , Blog blog){
		
		String email = getCurrentUserEmail();
		Blog updateuserBlog = blogService.updateUserBlog(email,blogId,blog);
		return new ResponseEntity<>(updateuserBlog,HttpStatus.OK);
	}
	
	  @DeleteMapping("/{blogId}")
      public ResponseEntity<Void> deleteUserBlog(@PathVariable int blogId){
		
		String email = getCurrentUserEmail();
		blogService.deleteUserBlog(email,blogId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
	
	
	private String getCurrentUserEmail() {
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
