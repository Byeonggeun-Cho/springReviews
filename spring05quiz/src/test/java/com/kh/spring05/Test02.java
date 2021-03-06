package com.kh.spring05;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

// [2] 독립 테스트로 목록과 검색을 한 번에 구현
public class Test02 {

	@Test
	public void teset() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		
		// 구문을 호출해서 검색 또는 목록조회 수행
		List<Product> list = sqlSession.selectList("product.list");
		
		for(Product product: list) {
			System.out.println(product);
		}
	}
	
}
