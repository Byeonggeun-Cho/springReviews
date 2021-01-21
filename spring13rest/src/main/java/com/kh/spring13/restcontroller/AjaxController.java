package com.kh.spring13.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController	// = @Controller + @ResponseBody
				// = jsp 없이 데이터만 전송하는 컨트롤러
				// = 최근에 증가 추세인 프론트엔드 프레임워크(React, Vue, Angular, ...)와 세트로 사용
@RequestMapping("/rest")
public class AjaxController {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	// jackson-databind가 있으면 자바의 모든 형태가 전부 json으로 변환
	// 없으면 String만 반환 가능
	@GetMapping("/id")
	public Map<String, Object> idCheck(@RequestParam String id) {
		// 1. 아이디 존재여부 검사
		int count = sqlSession.selectOne("member.find", id);
		
		// 2. 결과 반환

		/*
		 	{"count" : 1}
		 	1) 클래스 객체를 위의 형태로 생성
		 	2) Map을 이용
		 */
		
		Map<String, Object> map = new HashMap<>();
		map.put("count", count);

		return map;
	}
}
