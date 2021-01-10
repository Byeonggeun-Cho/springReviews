package com.kh.spring04;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.kh.spring04.entity.Student;

// 독립 테스트로 점수 수정을 구현
public class Test05 {

	@Test
	public void test() throws IOException {
		// 설정파일 로딩
		InputStream in = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
		// mybatis 준비
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 명령 실행도구 준비
		SqlSession sqlSession = factory.openSession(true);
		
		// 수정
		Student student = new Student().builder()
									.name("테스트")
									.score(60)
									.build();
		
		sqlSession.update("student.edit", student);
	}
	
}
