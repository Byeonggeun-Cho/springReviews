package com.kh.spring10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/first")
	@ResponseBody		// viewResolver를 사용하지 않겠다는 설정 -> text, json 등의 형식을 바로 반환할 때 사용
	public String first() {
		
		// ArithmeticException
		int a = 10/0;
		
		return "first";
	}
	
	
	@GetMapping("/second")
	@ResponseBody
	public String second() {
		
		// ArrayIndexOutOfBoundsException
		int[] a = new int[3];
		a[100] = 20;
		
		return "second";
	}
}
