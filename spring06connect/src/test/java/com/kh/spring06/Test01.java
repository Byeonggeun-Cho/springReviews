package com.kh.spring06;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 연동 설정 후 sqlSession을 사용할 수 있는지 확인

@RunWith(SpringJUnit4ClassRunner.class)	// 스프링과 연동할 것을 선언
// 스프링 설정 파일 연결
// 프로젝트 root : file
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class Test01 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		System.out.println(sqlSession);
	}
}
