package com.kh.spring15;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring15.Service.EmailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
})
@WebAppConfiguration
public class Test07 {

	// 목표: GmailService의 sendCertification() 기능 테스트
	@Autowired
	private EmailService emailService;
	
	@Test
	public void test() {
		emailService.sendCertification("hello");
	}
	
}
