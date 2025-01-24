package com.blogbeat.entry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.blogbeat.entry.entities.User;
import com.blogbeat.entry.repositories.UserRepository;

@Service
public class EmailService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
//	@Scheduled(cron = "0 0 10 * * ?") This will run at 10:00 AM every day.
//	@Scheduled(fixedRate = 60000)
	public void sendEmails() {
		Iterable<User> users = userRepository.findAll();
		for(User user : users) {
			sendEmail(user.getEmail(), "Todays offers", "Hello "+user.getName()+" Good news for you saurabh.");
		}
	}
	
	public void sendEmail(String to , String subject , String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}

}
