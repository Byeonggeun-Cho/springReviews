package com.kh.spring17;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.spring17.vo.pay.KakaoPayCancelReady;
import com.kh.spring17.vo.pay.KakaoPayCancelResult;

import lombok.extern.slf4j.Slf4j;

// 목표: 독립테스트로 "결제 취소"를 구현
@Slf4j
public class Test07 {
	private RestTemplate template;

	@Before
	public void before() {
		template = new RestTemplate();
	}
	
	@Test
	public void test() throws URISyntaxException {
		// 데이터 준비
		KakaoPayCancelReady ready = KakaoPayCancelReady.builder()
										.tid("T2863891700994784400")	// 파라미터로 전달 받는다
										.cancel_amount(6200)			// DB로부터 전달 받는다
										// .cancel_tax_free_amount(0)	// 없을 경우 제외
										.build();
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK dec23542eacb5b3b8af2e4b63d5b4ed3" );
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", "TC0ONETIME");
		body.add("tid", ready.getTid());
		body.add("cancel_amount", String.valueOf(ready.getCancel_amount()));
		body.add("cancel_tax_free_amount", String.valueOf(ready.getCancel_tax_free_amount()));
		
		HttpEntity<MultiValueMap<String, String>> entity =
				new HttpEntity<>(body, headers);
		
		URI uri = new URI("https://kapi.kakao.com/v1/payment/cancel");
		
		// Map<String, String> result =
		//		template.postForObject(uri, entity, Map.class);

		KakaoPayCancelResult result = 
				template.postForObject(uri, entity, KakaoPayCancelResult.class);
		
		log.info("result={}", result);
	}
}
