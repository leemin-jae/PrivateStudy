package com.ssafy.soldsolve.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

	private final JavaMailSender javaMailSender;


	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}
}