package com.kh.spring16.FrontendController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 웹소켓 화면으로 연결해주는 컨트롤러
@Controller
public class FrontendController {
	
	@GetMapping("/example1")
	public String basic() {
		return "websocket/example1";
	}
	
	@GetMapping("/example2")
	public String group() {
		return "websocket/example2";
	}
	
	@GetMapping("/example3/{room}")
	public String room(@PathVariable int room,
						Model model) {
		
		model.addAttribute("room", room);
		
		return "websocket/example3";
	}
}
