package com.kh.spring15;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring15.Service.EmailService;
import com.kh.spring15.entity.Member;

// 인증 컨트롤러(비동기 처리)
@RestController
@RequestMapping("/cert")
public class CertController {
	@Autowired
	private EmailService emailService;
	
	
	
	// 인증번호 보내기 요청
	@GetMapping("/send")
	public void send(HttpSession session) {
		Member member = (Member) session.getAttribute("user");
		
		String email = member.getId();
		emailService.sendCertification(email);
	}
	
	// 인증번호 확인 요청
	
}
