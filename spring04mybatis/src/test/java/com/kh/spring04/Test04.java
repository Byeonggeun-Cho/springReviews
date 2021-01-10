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

// 독립 테스트로 mybatis에서 이름 검색을 수행
public class Test04 {

	@Test
	public void test() throws IOException {
		// 설정파일 로딩
		InputStream in = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
		// mybatis 준비
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 명령 실행도구 준비
		SqlSession sqlSession = factory.openSession(true);
		
		
		// 이름을 전달하여 검색 수행
		String name = "하";
		
		List<Student> list = sqlSession.selectList("student.search", name);
		
		System.out.println("결과: " + list.size());
		
		for(Student student: list) {
			System.out.println(student);
		}
		
	}
}
