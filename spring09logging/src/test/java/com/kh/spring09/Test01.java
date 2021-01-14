package com.kh.spring09;

import java.time.LocalDateTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test01 {
	
	// Loger(로깅 도구 생성)
	// - import는 무조건 org.slf4j를 선택
	Logger log = LoggerFactory.getLogger(Test01.class);
	
	@Test
	public void test() {
		// 표준 출력
		// - 어느 위치에서 출력했는지 알 수 없음
		// - 언제 출력했는지 알 수 없음
		// - 언젠가는 지워야 함
		// - 출력 위치를 정할 수 없음(무조건 콘솔에 출력됨)
		
		System.out.println(LocalDateTime.now() + "Hello world!");
		
		// Logger를 이용한 출력
		log.info("Hello world!");
	}
}
