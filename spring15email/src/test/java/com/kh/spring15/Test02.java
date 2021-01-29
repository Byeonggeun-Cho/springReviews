package com.kh.spring15;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class Test02 {

	@Autowired
	private JavaMailSender sender;

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
