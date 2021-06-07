package com.hexaware.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

	@Autowired
	MailSender emailSender;

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public void sendEmail(@RequestBody Object booking) {
		System.out.println(booking);
		SimpleMailMessage message = new SimpleMailMessage();
		;
		message.setFrom("manu_casestudy@opentrash.com");
		message.setTo("manu_casestudy@opentrash.com");
		message.setSubject("Email confirmation for updates!!!");
		message.setText("Looks like something changed in ur booking");
		emailSender.send(message);
	}
}
