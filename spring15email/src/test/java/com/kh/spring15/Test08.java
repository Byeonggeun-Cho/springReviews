package com.kh.spring15;

import java.text.DecimalFormat;
import java.util.Random;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// 목표: 독립 테스트로 랜덤 인증번호(String) 생성
public class Test08 {

	@Test
	public void test() {
		Random rand = new Random();
		// 6자리 수 필요 0~999999까지 추첨 = 0~ 1000000개
		
		int number = rand.nextInt(1000000);
		
		log.info("number = {}", number);
		
		// DecimalFormat을 이용한 자리잡기
		// - 0은 숫자가 없어도 자리를 잡는 역할
		// - #은 숫자가 없으면 자리를 비우는 역할
		DecimalFormat df = new DecimalFormat("000000");
		String cert = df.format(number);
		
		log.info("cert = {}", cert);
	}
}
