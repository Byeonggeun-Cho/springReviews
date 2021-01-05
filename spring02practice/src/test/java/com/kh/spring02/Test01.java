package com.kh.spring02;

import org.junit.Test;

/**
 * TDD(Test driven Development
 * - 테스트 주도 개발
 * - 큰 규모의 서비스(ex: 웹)를 만들 때 부분별로 테스팅을 진행하면서 개발
 * - 작은 단위를 확실하게 개발하기 위한 방법
 * 
 * 1. 독립 테스트: Spring의 도구들을 사용하지 않는 테스트
 * 2. 연동 테스트: Spring의 도구들을 사용하는 테스트
 * - 연동을 위해서는 필요한 설정등이 존재
 */

public class Test01 {
	
	/**
	 * 독립테스트틀 위해서는 테스트 메소드가 필요
	 * - public
	 * - void
	 * - 매개변수 없음
	 * - @Test 필요
	 * - 메소드명은 자유
	 */

	@Test
	public void test() {
		System.out.println("Hello!");
	}
}
