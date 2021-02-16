package com.kh.spring17;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

// 독립 테스트를 통한 결제정보 조회 구현
@Slf4j
public class Test06 {
	private RestTemplate template;
	
	@Before
	public void before() {
		template = new RestTemplate();
	}

	@Test
	public void test() throws URISyntaxException {
		// 헤더, 바디, 엔티티(합성), 주소 추가, 전송, 확인
		
		String tid="T2863891700994784400";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK dec23542eacb5b3b8af2e4b63d5b4ed3" );
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();		
		body.add("cid", "TC0ONETIME");
		body.add("tid", tid);
		
		
		HttpEntity<MultiValueMap<String, String>> entity =
				new HttpEntity<>(body, headers);
		
		URI uri = new URI("https://kapi.kakao.com/v1/payment/order");
		
		Map<String, String> result = template.postForObject(uri, entity, Map.class);
	
		log.info("result={}", result);
	}
}
