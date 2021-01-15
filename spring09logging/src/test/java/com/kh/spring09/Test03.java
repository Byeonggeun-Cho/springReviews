package com.kh.spring09;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test03 {

	// 로깅 도구 또 생성?
//	final Logger log = LoggerFactory.getLogger(Test03.class);

	// - log4j에서는 지원하지 않지만 lombok에서 지원
	// - 사용하고 싶은 클래스 위에 @Slf4j 작성
	
	@Test
	public void test() {
		log.debug("디버그 수준");
		log.info("정보 수준");
		log.warn("경고 수준");
		log.error("오류 수준");
	}
	
}
