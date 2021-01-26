package com.kh.spring14.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect		// 간섭 객체임을 설정
@Component	// spring bean으로 등록
public class LoggingAspect {

	// 클래스 내부에 Advice를 만들어서 간섭을 수행
	// - 간섭 타이밍에 따라 구분
	// - Before, AfterReturning, AfterThrowing, After, Around
	
	
	// Advice  메소드 - @Before
	// target(com.kh.spring14.repository.MemberDaoImpl) - Pointcut(목적지)
	// execution, whithin, ... 등 방식 다양
	
	@Before("target(com.kh.spring14.repository.MemberDao)")			// implements 된 대상으로 자동으로 설정해줌
//	@Before("target(com.kh.spring14.repository.MemberDaoImpl)")
	public void before() {
		// 간섭할 코드 작성
		log.info("\nbefore 메소드 실행!\n");
	}
	
	@After("target(com.kh.spring14.repository.MemberDaoImpl)")
	public void after() {
		log.info("after 메소드 실행");
	}
}
