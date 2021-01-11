package com.kh.spring07.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring07.entity.Product;

@Controller
public class ProductController {
	@Autowired
	private SqlSession sqlSession;

/**
 *	등록 처리 컨트롤러
 *	- 주소를 2개로 나누어 처리
 *	- 마치면 반드시 리다이렉트 처리(새로고침 및 뒤로가기 방지) 
 */
	
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
		
//		System.out.println("name: " + product.getName());
//		System.out.println("price: " + product.getPrice());

		sqlSession.insert("product.add", product);
		
//		return "/WEB-INF/views/insert2.jsp";
//		return "insert2";

//		response.sendRedirect()	// 새로고침으로 재실행되지 않도록 redirect 수행		

		// redirect한 뒤 '뒤로가기' 실행하면 가장 가까운 200번 페이지 호출
		return "redirect:insert_finish";
	}
	
	// 302(임시 url변경) -> 200(성공)
	@GetMapping("/insert_finish")
	public String insertFinish() {
		return "insert2";
	}
	
/**
 * 목록 처리 컨트롤러
 * - 주소를 2개로 나눠서 처리
 */

	
	// Model은 JSP와 연결되어 있고, JSP에서 출력하 데이터를 전달할 때 사용
	// = control과 view를 연결하는 도구
	@GetMapping("/list")
	public String list(Model model,
						@RequestParam(required=false, defaultValue="no") String category,
						@RequestParam(required=false, defaultValue="asc") String order
			
			) {
		// 마이바티스를 이용해서 product 테이블 목록을 불러오고
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("order", order);
		
		List<Product> list = sqlSession.selectList("product.list", map);
		model.addAttribute("list", list);
		
		return "product/list";
	}
}





















