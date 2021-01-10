package com.kh.spring04;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

// mybatis에 필요한 도구들을 연동 없이 직접 생성 - 독립 테스트
public class Test01 {
	
	@Test
	public void test() throws IOException {
		// 설정파일 로딩
		InputStream in = Resources.getResourceAsStream("mybatis/myatis-config.xml");
		// mybatis 준비
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 명령 실행도구 준비
		SqlSession sqlSession = factory.openSession();
		
	}
	
}
