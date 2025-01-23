package com.userbeat.entry.service;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.userbeat.entry.entities.User;
import com.userbeat.entry.repositories.UserRepository;

@Service
public class EmailService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
//	@Scheduled(cron = "0 0 60 * * ?")
//	@Scheduled(fixedRate = 60000)
	public void sendEmails() {
		Iterable<User> users = userRepository.findAll();
		for(User user : users) {
			sendEmail(user.getEmail(), "Todays Offer","Hello "+user.getName()+" Good News For you saurabh");
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
