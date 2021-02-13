package com.kh.spring17;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

// 목표: 독립 테스트를 통해 Kakao 결제 API에 "결제요청" 메세지를 전송하는 것
public class Test01 {

	// 1. 도구 준비
	RestTemplate template;
	
	@Before
	public void before() {
		template = new RestTemplate();
	}
	
	@Test
	public void test() throws URISyntaxException {
		// 2. 요청 헤더 준비(편지봉투)
		// 결제요청 정보를 담을 대상
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization", "KakaoAK dec23542eacb5b3b8af2e4b63d5b4ed3" );
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	
		// 3. 요청 바디 준비(편지지)
		// - MultiValueMap은 Key 한 개에 value 여러 개가 연결될 수 있는 형태의 저장소
		// - 즉, Map<K, List<K>> 형태와 동일
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		
		// required parameter 정보 등록
		body.add("cid", "TC0ONETIME");
		body.add("partner_order_id", UUID.randomUUID().toString());	// 주문번호를 임의의 16Byte로 변환
		body.add("partner_user_id", UUID.randomUUID().toString());	// 사용자번호를 임의의 16Byte로 변환
		body.add("item_name", "아이스 아메리카노");	// 상품이름(사용자에게 표시)
		body.add("quantity", "1");	// 수량
		body.add("total_amount", "4500");	// 총 결제금액
		body.add("tax_free_amount", "0");	// 총 비과세액
		body.add("approval_url", "http://localhost:8089/spring17/pay/success");	// 성공시 수신할 주소
		body.add("fail_url", "http://localhost:8089/spring17/pay/fail");		// 실패시 수신할 주소
		body.add("cancel_url", "http://localhost:8089/spring17/pay/cancel");	// 취소시 수신할 주소
	
		// 4. 요청헤더와 바디를 합성(2+3)
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
		
		// 5. 주소 설정
		// 		URI(Uniform Resource Identifier): 리소스 식별자. 리소스의 위치 또는 Unique한 이름
		//		URL(Uniform Resource Locator): 리소스 위치 지정자.
		//			- protocol + domain + port + path to the file + parameters + anchor
		//				- protocol: 통신규약
		//				- domain name: ip address로 변환되는 문자열
		//				- port: 웹서버와 로컬 간 통신을 위한 gate 번호
		//				- path: web server의 리소스 경로 (최근에는 추상화된 경로 사용)
		//				- parameter: 인자값
		//				- anchor: 페이지 로드 시 anchor가 정의된 곳으로 이동(링크)
		//		URN(Uniform Resource Name): 리소스 이름
		URI uri = new URI("http://kapi.kakao.com/v1/payment/ready");
		
		// 6. 요청 전송
		template.postForLocation(uri, entity);
		
	}
	
}






























