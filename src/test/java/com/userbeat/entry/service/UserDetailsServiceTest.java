package com.userbeat.entry.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.User;

import com.userbeat.entry.repositories.UserRepository;

public class UserDetailsServiceTest {

	@InjectMocks
	private UserDetailServiceImpl detailServiceImpl;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void loadUserByUserNameTest() {
		
		when(userRepository.findByName(ArgumentMatchers.anyString())).thenReturn(User.builder().username("ram").password("ram").roles(new ArrayList<>()).build());
	}
}
