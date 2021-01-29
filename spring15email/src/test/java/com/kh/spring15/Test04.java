package com.kh.spring15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
public class Test04 {
	@Autowired
	private JavaMailSender sender;
	
	// 목표: 미리 만들어둔 템플릿(template.html)을 불러와서 전송
	// - 비밀번호 변경 링크
	// - 회원가입 환영 메세지
	@Test
	public void test() throws MessagingException, IOException {
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
		
		// 파일을 읽어올 도구를 만들어 템플릿 파일을 불러온다.
		ClassPathResource resource = new ClassPathResource("email/template.html");
		
		// 불러온 resource를 java.file 형식으로 변환
		File file = resource.getFile();
		
		// BufferedReader를 이용해 파일을 읽어옴
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		// BufferedReader를 이용해 읽어온 값을 String 형식으로 저장
		StringBuffer  buffer = new StringBuffer();
		
		// reader에서 읽은 한 줄을 buffer에 계속 더한다(문자열 합성기)
		while(true) {
			String line = br.readLine();	// 1줄씩 읽고
			if(line == null) {
				break;
			}
			buffer.append(line);
		}
		
		br.close();
		
		helper.setText(buffer.toString(), true);		// html 모드 설정
		
		// 전송
		sender.send(message);
		
		
	}
}
