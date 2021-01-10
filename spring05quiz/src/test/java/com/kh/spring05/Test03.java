package com.kh.spring05;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

// [3] 독립 테스트로 목록과 검색을 한 번에 구현
public class Test03 {

	@Test
	public void teset() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		
		// 구문을 호출해서 검색 수행
		String name = "처음";
		List<Product> list = sqlSession.selectList("product.list", name);
		
		for(Product product: list) {
			System.out.println(product);
		}
	}
	
}
