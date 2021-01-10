package com.kh.spring04;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.kh.spring04.entity.Student;

// 독립 테스트로 mybatis에서 조회를 수행
public class Test03 {

	@Test
	public void test() throws IOException {
		// 설정파일 로딩
		InputStream in = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
		// mybatis 준비
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 명령 실행도구 준비
		SqlSession sqlSession = factory.openSession(true);
		
		
		// select 구문 실행 요청
		List<Student>  list = sqlSession.selectList("student.list");
	
		for(Student student: list) {
			System.out.println(student);
		}
	}
}
