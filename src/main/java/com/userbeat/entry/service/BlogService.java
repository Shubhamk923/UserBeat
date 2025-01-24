package com.userbeat.entry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userbeat.entry.entities.Blog;
import com.userbeat.entry.entities.User;
import com.userbeat.entry.repositories.BlogRepo;
import com.userbeat.entry.repositories.UserRepository;

import jakarta.transaction.Transactional;
//import com.userbeat.entry.repositories.UserRepository;

@Service
@Transactional
public class BlogService {
	
	@Autowired
	BlogRepo blogRepo;
	
	@Autowired
	UserService userService;

	public Blog create(Blog blog , String email) {
		
		User user = getUserByEmailOrThrow(email);
		blog.setUser(user);
		return blogRepo.save(blog);
	}
	
	
	public User getUserByEmailOrThrow(String email) {
		
		return userService.getByGmail(email);
	}


	public List<Blog> getAllBlogs(String email) {
		User user = getUserByEmailOrThrow(email);
		return user.getBlogs();
	}


	public Blog getUserBlog(String email,int blogId) {
		User user = getUserByEmailOrThrow(email);
		
		return user.getBlogs().stream()
				.filter(blog->blog.getId() == blogId)
				.findFirst()
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT,"blog with id "+blogId+" not found"));
	}


//	public Blog updateUserBlog(String email, int blogId, Blog blog) {
//		User existingBlog = getUserBlog(email, blogId);
//		blog.setId(existingBlog.getId());
//		blog.setUser(existingBlog.getUser());
//		return blogRepo.save(blog);
//	}
	
	public Blog updateUserBlog(String email, int blogId , Blog blog) {
		Blog existingBlog = getUserBlog(email, blogId);
		blog.setId(existingBlog.getId());
		blog.setUser(existingBlog.getUser());
		return blogRepo.save(blog);
	}


	public void deleteUserBlog(String email, int blogId) {
		Blog blog = getUserBlog(email, blogId);
		blogRepo.deleteById(blog.getId());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	public Blog create(Blog blog, String email) {
//		User user = getUserByEmailOrThrow(email);
//		blog.setUser(user);
//		return blogRepo.save(blog);
//	}
//	
//	public List<Blog> getUserBlogs(String email) {
//		User user = getUserByEmailOrThrow(email);
//		return user.getBlogs();
//	}
//	
//	public Blog getUserBlog(String email, int blogId) {
//		User user = getUserByEmailOrThrow(email);
//		return user.getBlogs().stream()
//				.filter(blog->blog.getId() == blogId)
//				.findFirst()
//				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"blog with id "+blogId+" not found"));			
//	}
//	
//	
//	public Blog updateUserBlog(String email, int blogId , Blog blog) {
//		Blog existingBlog = getUserBlog(email, blogId);
//		blog.setId(existingBlog.getId());
//		blog.setUser(existingBlog.getUser());
//		return blogRepo.save(blog);
//	}
//	
//	public void deleteUserBlog(String email, int blogId) {
//		Blog blog = getUserBlog(email, blogId);
//		 blogRepo.deleteById(blog.getId());
//	}
//	
//	
//	public User getUserByEmailOrThrow(String email) {
//		return userService.getUserByEmail(email);
//	}





	
	




}
