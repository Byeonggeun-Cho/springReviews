package com.kh.spring16.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring16.repository.MemberDao;
import com.kh.spring16.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/login")
	public String login() {
//		return "/WEB-INF/views/login.jsp";
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute Member member,
						HttpSession session,
						@RequestParam(name = "room") int room,
						RedirectAttributes attr) {
		
		Member find = memberDao.login(member);
		
		if(find != null) {
			session.setAttribute("user", find);
		}

		return "redirect:/example4/" + room;
	};

	
	
	
	// 로그아웃 요청
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		
		return "redirect:/";
	}
	
}
