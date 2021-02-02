package com.kh.spring15;

import static org.junit.Assert.assertEquals;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring15.entity.Cert;

import lombok.extern.slf4j.Slf4j;

// 목표: 이메일과 인증번호가 주어졌을 때 인증번호가 유효한지 검사
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/Servlet-context.xml"
})
@WebAppConfiguration
@Slf4j
public class Test09 {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		// 데이터 준비: 이메일, 인증번호
		
		String email = "qptjtt@naver.com";
		String number = "198243";
			
		// 검사
		Cert cert = Cert.builder().who(email).what(number).build();
//		int count = sqlSession.selectOne("cert.check", cert);
		
		// 인증번호가 생성된지 5분 이내에만 인증 가능
		int count = sqlSession.selectOne("cert.checkWithTimeLimit", cert);

		// Slf4j의 기능 사용
		assertEquals(count, 1);		// 성공 시 기대값(count == 1)
		
		// count: 0 아니면 1
		if(count == 1) {
			log.info("인증 성공");
			
			// 인증 성공 시 인증정보 삭제
			sqlSession.delete("cert.remove", cert);
		} else {
			log.info("인증 실패");
		}
	}
}
