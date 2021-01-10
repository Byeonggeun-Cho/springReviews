package com.kh.spring05;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

// [4] 다양한 항목에 대한 검색
public class Test04 {

	@Test
	public void teset() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		String name = "처";
		int price = 1200;
		
		// 이름O 가격O: 검색
		// 이름O 가격X: 검색
		// 이름X 가격O: 검색
		// 이름X 가격X: 목록
		
		Map<String, Object> map = new HashMap<>();
//		map.put("name", name);
//		map.put("price", price);
		map.put("start", 500);
		map.put("end", 3000);

//		List<Product> list = sqlSession.selectList("product.list2");
		List<Product> list = sqlSession.selectList("product.list2", map);
		
		for(Product product: list) {
			System.out.println(product);
		}
	}
	
}
