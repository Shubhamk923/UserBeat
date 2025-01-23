package com.userbeat.entry.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestBody;
import com.userbeat.entry.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	public static final org.slf4j.Logger Logger = org.slf4j.LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		Logger.info("Configuring SecurityFilterChain");
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(authorize->authorize
		.requestMatchers("/public/**").permitAll()	
		.requestMatchers("/user/blogs/**","/users/**").hasRole("USER")
		.requestMatchers("/admin/users/**").hasRole("ADMIN")
		.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		Logger.info("Configuration AuthenticationProvider");
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	


}
