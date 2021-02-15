package com.kh.spring17;

import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring17.service.KakaoPayService;
import com.kh.spring17.vo.pay.KakaoPayRequestReady;
import com.kh.spring17.vo.pay.KakaoPayRequestResult;

import lombok.extern.slf4j.Slf4j;

// 목표: 연동 테스트를 통해 KakaoPayService의 ready가 정상 작동하는지 확인
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class Test03 {
	
	@Autowired
	private KakaoPayService kakaoPayService;

	@Test
	public void test() throws URISyntaxException {
		KakaoPayRequestReady ready = KakaoPayRequestReady.builder()
				.partner_order_id(UUID.randomUUID().toString())
				.partner_user_id(UUID.randomUUID().toString())
				.item_name("아이스 아메리카노")
				.quantity(1)
				.total_amount(4500)
				.build();
		
		KakaoPayRequestResult result = kakaoPayService.request(ready);
		log.info("result={}", result);
		
	}

}
