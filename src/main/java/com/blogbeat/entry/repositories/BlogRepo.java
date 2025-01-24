package com.blogbeat.entry.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blogbeat.entry.entities.Blog;
import com.blogbeat.entry.entities.User;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {
    List<Blog> findByUser(User user);
    
    @Query("SELECT b from Blog b WHERE b.id = :blogId AND b.user.id = :userId")
    Optional<Blog> findbyUserIdAndBlogId(@Param("userId") int userId , @Param("blogId") int blogId);
}







