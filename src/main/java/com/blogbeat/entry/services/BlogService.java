package com.blogbeat.entry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.blogbeat.entry.entities.Blog;
import com.blogbeat.entry.entities.User;
import com.blogbeat.entry.repositories.BlogRepo;

//import jakarta.validation.constraints.AssertFalse.List;

@Service
public class BlogService {

	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private UserService userService;

	public Blog create(Blog blog , String email) {
		 // Retrieve the user associated with the given email
		User user = getUserByEmailOrThrow(email);
		blog.setUser(user);
		return blogRepo.save(blog);
	}
	
	public List<Blog>  getUserBlogs(String email) {
		User user = getUserByEmailOrThrow(email);
//		return this.blogRepo.findByUser(user);
		 // Return the list of blogs associated with the user
		return user.getBlogs();
	}
	
	public Blog getUserBlog(String email, int blogId) {
		User user = getUserByEmailOrThrow(email);
//		return this.blogRepo.findbyUserIdAndBlogId(id, blogId).orElseThrow(
//				()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id "+id+" and blog with id "+blogId+" not found")
//				);
		return user.getBlogs().stream()
				.filter(blog -> blog.getId() == blogId)
				.findFirst()
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"blog with Id "+blogId+" not found"));
	}
	
	
	public void deleteUserBlog(String email,int blogId) {
		Blog blog = getUserBlog(email, blogId);
		blogRepo.deleteById(blog.getId());
	}

	public Blog updateUserBlog(String email, int blogId, Blog blog) {
		Blog existingBlog = getUserBlog(email, blogId);
		blog.setId(existingBlog.getId());
		blog.setUser(existingBlog.getUser());
		return blogRepo.save(blog);
	}
	
	public User getUserByEmailOrThrow(String email) {
		return userService.getUserByEmail(email);
	}
	
	
	
	
	
	
	
}
