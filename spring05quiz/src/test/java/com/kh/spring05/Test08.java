package com.kh.spring05;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

// [7] 단일항목 조회: 겹치면 오류 발생
public class Test08 {

	@Test
	public void teset() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);

		// 단일조회 수행: Primary Key 또는 Unique
		// = 결과의 형태는 DTO/VO
		long no = 8;
		Product product = sqlSession.selectOne("product.find", no);
		System.out.println("product: " + product);
	}
}