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

@Slf4j
public class Test04 {

	private RestTemplate template;
	
	@Before
	public void before() {
		template = new RestTemplate();
	}
	
	@Test
	public void test() throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK dec23542eacb5b3b8af2e4b63d5b4ed3" );
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 임시 저장값
		String partner_order_id = "3b42114c-1d50-41a9-9cd6-bf766f9245cf";
		String partner_user_id = "11f8013e-28d5-4615-a11b-a9f7f36b5150";
		String tid = "T2863655649592195228";
		String pg_token = "da09cb2f7a46acc0d1b4";
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", "TC0ONETIME");
		body.add("tid", tid);
		body.add("partner_order_id", partner_order_id);
		body.add("partner_user_id", partner_user_id);
		body.add("pg_token", pg_token);
		
		
		HttpEntity<MultiValueMap<String, String>> entity =
				new HttpEntity<>(body, headers);
		
		URI uri = new URI("https://kapi.kakao.com/v1/payment/approve");
		
		Map<String, String> result =
				template.postForObject(uri, entity, Map.class);
		
		log.info("result={}", result);
	}
}
