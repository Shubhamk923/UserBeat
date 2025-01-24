package com.userbeat.entry.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.userbeat.entry.repositories.UserRepository;

@SpringBootTest
public class ServiceTest {

	@Autowired
	UserRepository userRepository;
	
	@ParameterizedTest
	@CsvSource({
		"shubham",
		"rahul",
		"kene"
	})
	public void testfindbyusername(String name) {
		assertEquals(4, 3+1);
	    assertNotNull(userRepository.findByName(name));
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,1,2",
		"2,2,4",
		"10,2,12"
	})
	public void test(int a , int b , int expected) {
		assertEquals(expected, a+b);
	}
}
