package com.kh.spring05;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

// [8] 단일항목 수정
public class Test09 {

	@Test
	public void teset() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		Product product = Product.builder()
									.no(1)
									.name("참이슬")
									.price(1300)
									.build();
		int count = sqlSession.update("product.edit", product);
		
		System.out.println("count: " + count);
		
	}
}