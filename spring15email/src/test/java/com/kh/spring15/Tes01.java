package com.kh.spring15;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// 목표: 독립 테스트로 이메일 발송

public class Tes01 {
	
	private JavaMailSenderImpl sender;
	
	@Before
	public void before() {
		// sender 생성
		sender = new JavaMailSenderImpl();
		
		sender.setHost("smtp.gmail.com");		// 이메일 서비스 호스트
		sender.setPort(587);					// 이메일 서버 포트
		
		// 이메일 계정 인증 정보
		sender.setUsername("pumpkin.cstem@gmail.com");		// 사용자 이메일 주소
		sender.setPassword("xptmxmrPwjd");					// 사용자 비밀번호
		
		// 옵션 설정
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");				// 인증 설정
		prop.setProperty("mail.smtp.starttls.enable", "true");	// tls 사용 설정 - 공개키 암호화를 이용해 안전하게 전송
																// 공개키: 200자리 이상 소수인 서로소 사용
//		prop.setProperty("mail.smtp.debug", "true");			// 디버그 설정(옵션)
		
		// 옵션 저장
		sender.setJavaMailProperties(prop);
	}
	
	@Test
	public void test() {
		// 메일 전송
		// 1. 간단한 단문 메세지:  SimpleMailMessage
		// SimpleMailMessage 객체에 값을 넣어 전송
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		// 제목
		message.setSubject("이번주 로또 번호는!?");

		// 수신인
		String[] to = {"pumpkin.cstem@gmail.com"};
		message.setTo(to);
		
		// 참조(cc, carbon copy)
		String[] cc = {};
		message.setCc(cc);
		
		// 숨은참조(bcc, blind carbon copy)
		String[] bcc = {"qptjtt@naver.com"};
		message.setBcc(bcc);
		
		// 내용
		String text = "나도 모름ㅋ";
		message.setText(text);
		
		// 전송
		sender.send(message);
	}
}
