package com.kh.spring11;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
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
public class Test04 {
	
	@Autowired
	private MemberDao memberDao;
	
	private Member member;

	
	// 1. 테스트는 전 > 중 > 후로 구분된다(@Before -> @Test -> @After)
	// 2. 테스트 결과에 대한 기대치를 검증할 수 있다(assert 구문)
	//	- JUnit은 기본적으로 코드만 정상적으로 싱행되면 성공이라고 판단
	//	- 우리는 코드 정상실행과 더불어 로그인도 성고해야 성공이라고 판정
	
	@Before	// 테스트 전에 실행되는 메소드  <-> @After
	public void before() {
		member = Member.builder().id("hello").pw("a12341").build();
	}
	
	@Test
	public void loginTest() {
	
		Member find = memberDao.login(member);
		
		log.info("find = {}", find);
		
		assertNotNull(find);	// find가 not null인 경우에만 성공으로 처리
	}

}
