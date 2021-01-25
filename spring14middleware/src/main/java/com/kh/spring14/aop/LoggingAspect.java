package com.kh.spring14.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect//간섭 객체임을 설정
@Component//spring bean으로 등록
public class LoggingAspect {

	//클래스 내부에 Advice를 만들어서 간섭을 수행
	// - 간섭(잔소리) 타이밍에 따라 구분
	// - Before, AfterReturning, AfterThrowing, After, Around

	@Before("target(com.kh.spring14.repository.MemberDao)")
	public void before() {
		//간섭할 코드 작성
		log.info("before 메소드 실행!");
	}
	
}
