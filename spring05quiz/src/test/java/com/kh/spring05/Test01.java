package com.kh.spring05;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

// [1] 독립 테스트로 상품 등록 구현
public class Test01 {
	
	@Test
	public void test() throws IOException {

		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		// 데이터를 준비해서 insert를 수행
		
		Product product = Product.builder()
									.name("테라")
									.price(3000)
									.build();
		
		sqlSession.insert("product.add", product);
	}
	
}
