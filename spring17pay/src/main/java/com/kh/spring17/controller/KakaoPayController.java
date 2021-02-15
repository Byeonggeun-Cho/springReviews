package com.kh.spring17.controller;

import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring17.service.KakaoPayService;
import com.kh.spring17.vo.pay.KakaoPayRequestReady;
import com.kh.spring17.vo.pay.KakaoPayRequestResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/pay")
public class KakaoPayController {
	
	@Autowired
	private KakaoPayService kakaoPayService;
	
	// 결제하기 버튼이 존재하는 페이지
	@GetMapping("/confirm")
	public String confirm() {
		// return "/WEB-INF/views/pay/confirm.jsp";
		return "pay/confirm";
	}
	
	// 결제 요청 처리 매핑
	@PostMapping("/confirm")
	public String confirm2() throws URISyntaxException {
		// Test03에서 수행한 작업을 그대로 구현
		
		// 임시 정보 생성(추후 삭제)
		KakaoPayRequestReady ready = KakaoPayRequestReady.builder()
				.partner_order_id(UUID.randomUUID().toString())
				.partner_user_id(UUID.randomUUID().toString())
				.item_name("아이스 아메리카노")
				.quantity(1)
				.total_amount(4500)
				.build();
		
		
		// 서비스 호출
		// 결제사에 결제요청 후 응답
		KakaoPayRequestResult result = kakaoPayService.request(ready);

		log.info("partner_order_id={}", ready.getPartner_order_id());
		log.info("partner_user_id={}", ready.getPartner_user_id());
		log.info("tid={}", result.getTid());

		// 사용자에게 카카오페이지 결제화면으로 리다이렉트할 것을 지시
		return "redirect:" + result.getNext_redirect_pc_url();
	}
}