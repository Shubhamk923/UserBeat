package com.blogbeat.entry.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogbeat.entry.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	List<User> findByName(String name);
	

}
