package com.kh.spring07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

//	@RequestMapping(value="/insert", method=RequestMethod.GET)
	@GetMapping("/insert")
	public String insert() {
		
//		return "/WEB-INF/views/insert.jsp";
		return "insert";
	}
	
	
//	@RequestMapping(value="/insert" method=RequestMethod.POST)
//	@PostMapping("/insert")
//	public String insert() {
//		
//		return "";
//	}
}
