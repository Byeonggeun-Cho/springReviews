package com.kh.spring15.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kh.spring15.entity.Cert;

@Service
public class GmailService implements EmailService {

	@Autowired
	private JavaMailSender sender;
	
	@Override
	public void sendCertification(String id) {
		// 1. 인증번호 객체 생성
		Cert cert = Cert.builder().who(id).what("123123").build();
		// 2. DB 등록
		
		// 3. 이메일 발송
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("pumpkin.cstem@gmail.com");
		message.setTo(id);
		message.setSubject("인증번호 테스트");
		message.setText("인증번호: " + cert.getWhat());

		sender.send(message);
		
		
	}

}
