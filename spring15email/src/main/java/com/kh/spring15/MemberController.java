package com.kh.spring15;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.spring15.entity.Member;
import com.kh.spring15.repository.MemberDao;

@Controller
public class MemberController {
	@Autowired
	private MemberDao memberDao;
	
	// 로그인 요청: 임시 아이디로 로그인
	@GetMapping("/login")
	public String login(HttpSession session) {
		Member member = Member.builder()
								.id("qptjtt@naver.com")
								.pw("b1234")
								.build();
		
		Member find = memberDao.login(member);
		
		if(find != null) {
			session.setAttribute("user", find);
		}
		
		return "redirect:/";
	};
	
	// 로그아웃 요청
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		
		return "redirect:/";
	}
	
}
