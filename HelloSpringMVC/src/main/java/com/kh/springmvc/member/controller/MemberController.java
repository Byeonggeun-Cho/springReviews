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
						Model model) {
		
		System.out.println("pageNum: " + pageNum + ", size: " + size);
		
//		System.out.println("service: " + service);
		
//		List<Member> members = service.findMembers(pageNum, size);
		
//		System.out.println(members);
		
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
	
	
	
	
}
