package com.kh.springmvc.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.springmvc.member.service.MemberService;
import com.kh.springmvc.member.vo.Member;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	private MemberService service;
	
	// 생성자 주입으로 NullPointerException 예방
	@Autowired
	public MemberController(MemberService service) {
		this.service = service;
	}
	
	/*
	 * 클라이언트에서 요청을 입력받는 방법
	 * 1. 쿼리 파라미터로 요청 받기
	 * 	- /member/list?rageNum2=&size=10
	 * 	- @RequestParam 어노테이션을 통해 사용
	 * 
	 * 2. 패스 변수로 요청 받기
	 * 	- 패스에 있는 값을 담아내기 위해 {}(플레이스홀더)를 사용하여 패스에 있는 값을 담아낸다.
	 * 	- /member/{id}
	 * 	- @PathVariable 어노테이션을 통해 사용
	 * 
	 * 3. form 태그를 통해 요청 받기
	 */
	

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@RequestParam(value="pageNum", defaultValue="1") int pageNum,
						@RequestParam(value="size", defaultValue="5") int size,
//						HttpServletRequest request,
//						HttpSession session,
						Model model) {
		
		System.out.println("pageNum: " + pageNum + ", size: " + size);
		
//		System.out.println("service: " + service);
		
//		List<Member> members = service.findMembers(pageNum, size);
		
//		System.out.println(members);
		
		
//		request.setAttribute("members", service.findMembers(pageNum, size));
//		session.setAttribute("members", service.findMembers(pageNum, size));
		
		model.addAttribute("members", service.findMembers(pageNum, size));
		
		return "member/list";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String detatil(@PathVariable("id") long id,
							Model model) {
		
		System.out.println("id: " + id);
		
		Member member = service.findById(id);
		
		model.addAttribute("member", member);
		
		return "member/detail";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegisterform() {
		
		return "member/register";
	}
	
	/*
	 * 사용자로부터 전달된 값을 읽어오는 방법
	 * 1. HttpServletRequest 사용하기
	 *  - 메소드 파라미터로 HttpServletRequest request 추가 후
	 *    request.getParameter("name")을 통해 읽어온다.
	 * 2. @RequestParam으로 값을 하나하나 받아오기
	 * 3. CommandMap 객체 활용하기
	 *  - Spring에서 매개변수로 변수명이 아닌 vo를 명시하면
	 *    vo의 필드변수의 이름과 @RequestParam의 변수명을 비교하여 같은 이름일 경우 vo에 한 번에 담아올 수 있다.
	 *    
	 */
	
	@RequestMapping(value="register", method=RequestMethod.POST)
//	public String register(HttpServletRequest request) {
//	public String register(@RequestParam String name,
//							@RequestParam int age,
//							@RequestParam String gender,
//							@RequestParam String address) {
	public String register(Member member) {	
	
//		System.out.println(member.getName());
//		System.out.println(member.getAge());
//		System.out.println(member.getGender());
//		System.out.println(member.getAddress());

		int memberId = service.save(member);

		// form 처리가 완료되고 나면 redirect 처리하는 것이 POST 처리할 때 사용되는 좋은 방법이다.
		// (refresh로 인해 중복된 form 제출을 막을 수 있다.)
		return "redirect:/member/" + memberId;
	}
	
	
}
