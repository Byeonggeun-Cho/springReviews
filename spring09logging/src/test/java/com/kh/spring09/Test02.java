package com.kh.spring09;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test02 {

	// 로깅 도구(Logger) 생성
	Logger log = LoggerFactory.getLogger(Test02.class);
	
	@Test
	public void test() {
		log.debug("디버그 수준");
		log.info("정보 수준");
		log.warn("경고 수준");
		log.error("오류 수준");
	}
}
