package com.kh.spring15;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class Test03 {
	@Autowired
	private JavaMailSender sender;
	
	// 목표: 복잡한 메세지(MimeMessage)를 만들어 전송
	@Test
	public void test() throws MessagingException {
		// 글자만 전송 가능
		// SimpleMailMessage message = new SimpleMailMessage();

		MimeMessage message = sender.createMimeMessage();
		
		// 헬퍼(설정 도우미) 설정
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
		
		// 메세지 내용 설정
		helper.setFrom("pumpkin.cstem@gmail.com");
		helper.setSubject("행운의 편지");
		
		String[] to = {"qptjtt@naver.com"};
		helper.setTo(to);
		
		// 참조(cc), 숨은참조(bcc) 설정
		String[] cc = {};
		helper.setCc(cc);
		
		String[] bcc = {};
		helper.setBcc(bcc);
		
		helper.setText("<h1>이 편지는 영국...</h1>", true);
		
		// 전송
		sender.send(message);
		
		
	}
}
