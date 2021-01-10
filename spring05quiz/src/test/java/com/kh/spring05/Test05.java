package com.kh.spring05;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

// [5] 정렬을 자유자재로 할 수 있도록 테스트 구현
public class Test05 {

	@Test
	public void teset() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		// 데이터 준비
		Map<String, Object>	map = new HashMap<>();
		map.put("category", "no");
		map.put("order", "desc");
		
		List<Product> list = sqlSession.selectList("product.list3", map);

		for(Product product: list) {
			System.out.println(product);
		}
	}
	
}
