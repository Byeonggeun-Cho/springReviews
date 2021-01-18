package com.kh.spring11;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring11.entity.Member;
import com.kh.spring11.repository.MemberDao;

import lombok.extern.slf4j.Slf4j;

// 목표: 연동 테스트를 통해 회원 가입 처리를 구현
// - 테스트를 먼저 진행하여 개발하는 기법(TDD: Test Driven Development. 테스트 주도 개발 기법)

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
})
@WebAppConfiguration	// 가상의 web.xml 사용
public class Test03 {
	
	@Autowired
	private MemberDao memberDao;
	
	@Test
	public void test() {
		
		// 회원 1명의 정보를 임의로 생성
		Member member = Member.builder().id("hello2").pw("b1234").build();
		
		memberDao.join(member);
	}

}
