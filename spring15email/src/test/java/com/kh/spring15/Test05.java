package com.kh.spring15;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
public class Test05 {
	@Autowired
	private JavaMailSender sender;
	
	// 목표: 발송 메일에 첨부파일 추가
	@Test
	public void test() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setFrom("pumpkin.cstem@gmail.com");
		helper.setTo("qptjtt@naver.com");
		helper.setSubject("첨부파일 테스트");
		helper.setText("내용 없음");
		
		// helper를 이용하여 첨부파일 추가
		// - javax.activation.DataSource: 자원 (O)
		// - javax.sql.DataSource: DB 연결자원 (X)
		
		// FileDataSource를 불러와 DataSource 형식으로 저장하여 첨부
		DataSource source = new FileDataSource("D:/images/rice.jpg");
		
		// 파일명에 띄어쓰기 사용 가능
		// 실제파일명 -> source.getName()
		helper.addAttachment("볶음밥", source);
		
		// 전송
		sender.send(message);
		
	}
}
