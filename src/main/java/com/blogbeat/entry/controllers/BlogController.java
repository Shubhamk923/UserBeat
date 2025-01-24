package com.blogbeat.entry.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import org.springframework.web.server.ResponseStatusException;

import com.blogbeat.entry.entities.Blog;
import com.blogbeat.entry.entities.User;
import com.blogbeat.entry.services.BlogService;

@RestController
@RequestMapping("/user/blogs")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	
	
	
	@GetMapping
	public ResponseEntity<List<Blog>>  getAllBlogs() {
		String email = getCurrentUserEmail();
		List<Blog> blogs = blogService.getUserBlogs(email);
		return new ResponseEntity<>(blogs,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
		String email = getCurrentUserEmail();
		Blog createBlog = blogService.create(blog, email);
		return new ResponseEntity<>(createBlog,HttpStatus.CREATED);
	}
	
	@GetMapping("/{blogId}")
	public ResponseEntity<Blog>  getUserBlog(@PathVariable int blogId) {
		String email = getCurrentUserEmail();
		try {
			Blog blog = blogService.getUserBlog(email,blogId);
			return new ResponseEntity<>(blog,HttpStatus.OK);
		}catch(ResponseStatusException e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PutMapping("/{blogId}")
	public ResponseEntity<Blog>  updateBlog( @PathVariable int blogId , @RequestBody Blog blog) {
		String email = getCurrentUserEmail();
		try {
			Blog updatedBlog = blogService.updateUserBlog(email, blogId,blog);
			return new ResponseEntity<>(updatedBlog,HttpStatus.OK);
		}catch(ResponseStatusException e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{blogId}")
	public ResponseEntity<Void>  deleteUserBlogById(@PathVariable int blogId) {
		String email = getCurrentUserEmail();
		try {
			blogService.deleteUserBlog(email, blogId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(ResponseStatusException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	private String getCurrentUserEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
		}
	
	
	
	
}
