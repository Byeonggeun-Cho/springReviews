package com.kh.spring14.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import jdk.internal.jline.internal.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect		// 간섭 객체임을 설정
@Component	// Spring bean으로 등록
public class LoggingAspect {
	// 클래스 내부에 Advice를 만들어서 간섭을 수행
	// - 간섭 타이밍에 다라 구분
	//	- Before, AfterReturning, AfterThrowing, After, Around
	
	@Before("target(com.kh.spring14.repository.MemberDaoImpl)")		// JUnit의 Before와 혼동 X
	public void before() {
		// 간섭 내용 작성
		
		log.info("before 메소드 실행!");
	}
	
	
}
