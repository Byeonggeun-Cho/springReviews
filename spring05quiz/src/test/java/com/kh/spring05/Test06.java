package com.kh.spring05;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

// [5] 특정 목록에 포함되는 항목을 검색(in 연산)
public class Test06 {

	@Test
	public void teset() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		// 목록을 준비해서 전달한 뒤 구문 실행
		// = 참이슬, 처음처럼, 테라, ... = String[] = List<String>
		
		List<String> require = Arrays.asList("참이슬", "처음처럼", "테라");	// 자바8
//		List<String> require = List.of("참이슬", "처음처럼", "테라");	// 자바9
		
		// list 형태로 전달하는 경우
//		List<Product> list = sqlSession.selectList("product.list4",require);
		
		// map 형태로 전달하는 경우
		// require를 map에 이름을 붙여 저장한 뒤에 전송
		Map<String, Object> map = new HashMap<>();
		map.put("require", require);
		
		List<Product> list = sqlSession.selectList("product.list5", map);

		for(Product product: list) {
			System.out.println(product);
		}
	}
}