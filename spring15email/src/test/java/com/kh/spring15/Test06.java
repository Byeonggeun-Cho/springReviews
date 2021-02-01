package com.kh.spring15;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring15.entity.Cert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
})
@WebAppConfiguration
public class Test06 {
	@Autowired
	private SqlSession sqlSession;
	
	// 목표: 데이터베이스에 인증번호를 넣어 추가 또는 갱신이 잘 이루어지는지 확인
	
	@Test
	public void test() {
		// 인증번호 객체 생성: who(회원아이디), what(인증번호), when(설정x)
		Cert cert = Cert.builder().who("hello").what("123456").build();
		
		// 등록
		sqlSession.insert("cert.add", cert);
	}
	
}
