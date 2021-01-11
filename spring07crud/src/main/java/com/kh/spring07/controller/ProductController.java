package com.kh.spring07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.spring07.entity.Product;

@Controller
public class ProductController {

//	@RequestMapping(value="/insert", method=RequestMethod.GET)
	@GetMapping("/insert")
	public String insert() {
		
//		return "/WEB-INF/views/insert.jsp";
		return /*prefix*/"insert"/*surfix*/;
	}
	
	/*
	 * @RequestParam: 파라미터 1개를 받는 설정
	 * @ModelAttribute: 주어진 객체에 알아서 잘 받는 설정
	 */
	
	
//	@RequestMapping(value="/insert" method=RequestMethod.POST)
	@PostMapping("/insert")
	public String insert(
//						@RequestParam String name,
//						@RequestParam int price
						@ModelAttribute Product product
						) {
		
//		System.out.println("name: " + name);
//		System.out.println("price: " + price);
		
		System.out.println("name: " + product.getName());
		System.out.println("price: " + product.getPrice());
		
		return "";
	}
}
