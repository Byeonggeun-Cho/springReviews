package com.kh.spring17.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.spring17.vo.pay.KakaoPayRequestReady;
import com.kh.spring17.vo.pay.KakaoPayRequestResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaoPayServiceImpl implements KakaoPayService {

	@Override
	public KakaoPayRequestResult request(KakaoPayRequestReady ready) throws URISyntaxException {
		// 1. 전송도구 생성
		RestTemplate template = new RestTemplate();
		
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
		body.add("cid", "TC0ONETIME");		// 가맹점의 코드
		body.add("partner_order_id", ready.getPartner_order_id());	// 서버(가맹점)에서 임의로 만든 주문번호를 32개의 16진수로 표현되며 총 36개 문자(32개 문자와 4개의 하이픈)로 변환(결제 테이블 PK)
		body.add("partner_user_id", ready.getPartner_user_id());	// 서버(가맹점)에서 임의로 만든 사용자번호를 32개의 16진수로 표현되며 총 36개 문자(32개 문자와 4개의 하이픈)로 변환(사용자 테이블 PK)
		body.add("item_name", ready.getItem_name());	// 상품이름(사용자에게 표시)
		body.add("quantity", String.valueOf(ready.getQuantity()));	// 수량
		body.add("total_amount", String.valueOf(ready.getTotal_amount()));	// 총 결제금액
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
		URI uri = new URI("https://kapi.kakao.com/v1/payment/ready");
		
		// 6. 요청 전송
		// - postForLocation은 전송만 하는 명령
		// - postForObject는 전송 후 응답을 수신하는 명령
		// template.postForLocation(uri, entity);
		
		// Map으로 받는 방법
		// Map<String, String> result = template.postForObject(uri, entity, Map.class);
		// log.info("result={}", result);
		
		// 클래스 객체로 받는 방법
		KakaoPayRequestResult result = template.postForObject(uri, entity, KakaoPayRequestResult.class);
		log.info("result={}", result);
				
		return result;
	}

}
