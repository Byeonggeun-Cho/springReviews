package com.kh.spring03;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

// Spring에 의지하지 않고 등록한 mybatis 의존성을 가지고 직접 도구 생성
// 독립 테스트로 진행

public class Test01 {

	// mybatis에서 사용하는 도구
	// SqlSessionFactory: 명령 실행을 위한 환경을 구축해주는 역할
	// -> 설정 파일(.xml)을 불러와야 함
	// SqlSession: 실제 명령을 실행하는 도구

	@Test
	public void test() throws IOException {
		
		// 설정 로딩
		InputStream in = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
		
		// 도구 생성
		// SqlSessionFactory는 interface이므로 SqlsessionFactoryBuilder를 이용하여 객체 생성
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		System.out.println(factory);
		
		SqlSession sqlSession = factory.openSession();
		System.out.println(sqlSession);
	}
}
