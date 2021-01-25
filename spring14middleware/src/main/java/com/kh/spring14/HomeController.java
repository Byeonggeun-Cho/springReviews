package com.kh.spring14;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.spring14.entity.Member;
import com.kh.spring14.repository.MemberDao;

import jdk.internal.jline.internal.Log;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@Autowired
	private MemberDao memberDao;
	
	// 로그인 처리 컨트롤러
	// - 세션에 user라는 이름으로 admin이라는 값을 저장(샘플 아이디)
	// - 루트 페이지(/)로 리다이렉트
	
	@GetMapping("/login")
	public String login(HttpSession session
//						,@ModelAttribute Member member
						) {
//		session.setAttribute("user", "admin");
		
		// 예시 파일로 입력
		Member find = memberDao.login(Member.builder()
												.id("hello")
												.pw("a1234")
												.build());

		if(find != null) {
			session.setAttribute("user", find.getId());
		}
		
		return "redirect:/";
	}
	
	// 로그아웃 처리 컨트롤러
	// - 세션에 있는 user 정보를 삭제
	// - 루트 페이지(/)로 리다이렉트
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// session.invalidate();			// session이 삭제 됨
		session.removeAttribute("user");	// session 내 특정 속성값만 삭제
		

		return "redirect:/";
	}
	
	// 회원 전용 페이지
	@GetMapping("/member")
	public String member(Model model) {
		
		model.addAttribute("test", "hello");
		
		return "member";		// WEB-INF/views/member.jsp
	}
	
	// 일반 페이지
	@GetMapping("/normal")
	public String normal() {
		return "normal";		// WEB-INF/views/normal.jsp
	}
}





