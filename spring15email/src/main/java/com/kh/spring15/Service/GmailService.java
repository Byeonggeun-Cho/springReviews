package com.kh.spring15.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kh.spring15.entity.Cert;
import com.kh.spring15.repository.CertDao;

@Service
public class GmailService implements EmailService {

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private CertService certService;
	
	@Override
	public void sendCertification(String id) {
		// 1. 인증번호 객체 생성
		// 2. DB 등록 - sqlSession / CertDao
		// 1,2번은 CertService에서 처리
		String number = certService.create(id);
		
		// 3. 이메일 발송
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("pumpkin.cstem@gmail.com");
		message.setTo(id);
		message.setSubject("인증번호 테스트");
		message.setText("인증번호: " + number);
		
		sender.send(message);
	}

}
