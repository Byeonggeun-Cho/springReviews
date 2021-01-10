package com.kh.spring04;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.kh.spring04.entity.Student;

// mybatis에 필요한 도구들을 연동 없이 직접 생성 - 독립 테스트
public class Test02 {
	
	@Test
	public void test() throws IOException {
		// 설정파일 로딩
		InputStream in = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
		// mybatis 준비
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 명령 실행도구 준비
		SqlSession sqlSession = factory.openSession(true);
	
		// student-mapper.xml의 add 구문 호출
		// - 구문을 호출하며 데이터를 전달할 때는 규칙이 존재
		// - 반드시 데이터는 1개의 묶음으로 전달
		//	(1) Map으로 묶어서 전달
		//	(2) 클래스의 객체(vo)로 묶어서 전달
		
		Student student = new Student().builder()
									.name("파이리")
									.score(85)
									.build();
		
		sqlSession.insert("student.add2", student);
		
	}
	
}
