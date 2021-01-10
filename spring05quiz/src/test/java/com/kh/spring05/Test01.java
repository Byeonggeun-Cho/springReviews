package com.kh.spring05;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

// [1] 독립 테스트로 상품 등록 구현
public class Test01 {
	
	@Test
	public void test() throws IOException {

		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		
	}
	
}
