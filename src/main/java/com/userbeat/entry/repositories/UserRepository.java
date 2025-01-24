package com.userbeat.entry.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userbeat.entry.entities.User;

//import jakarta.validation.constraints.AssertFalse.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
	List<User> findByName(String name);
}
