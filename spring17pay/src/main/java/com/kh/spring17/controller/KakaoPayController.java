package com.kh.spring17.controller;

import java.net.URISyntaxException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring17.service.KakaoPayService;
import com.kh.spring17.vo.pay.KakaoPayApproveReady;
import com.kh.spring17.vo.pay.KakaoPayApproveResult;
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
	public String confirm(HttpSession session) throws URISyntaxException {
		// Test03에서 수행한 작업을 그대로 구현
		
		// 임시 정보 생성(추후 삭제)
		KakaoPayRequestReady ready = KakaoPayRequestReady.builder()
				.partner_order_id(UUID.randomUUID().toString())
				.partner_user_id(UUID.randomUUID().toString())
				.item_name("롱블랙")
				.quantity(1)
				.total_amount(5200)
				.build();
		
		
		// 서비스 호출
		// 결제사에 결제요청 후 응답
		KakaoPayRequestResult result = kakaoPayService.request(ready);

//		log.info("partner_order_id={}", ready.getPartner_order_id());
//		log.info("partner_user_id={}", ready.getPartner_user_id());
//		log.info("tid={}", result.getTid());

		// 세션에 결제 승인을 위해서 필요한 정보를 추가
		session.setAttribute("KakaoPayApproveReady",
				KakaoPayApproveReady.builder()
					.partner_order_id(ready.getPartner_order_id())
					.partner_user_id(ready.getPartner_user_id())
					.tid(result.getTid())
					.build());
		
		// 사용자에게 카카오페이지 결제화면으로 리다이렉트할 것을 지시
		return "redirect:" + result.getNext_redirect_pc_url();
	}
	
	// 성공 처리 매핑
	// - Kakao API에서 pg_token이라는 파라미터를 보내기 때문에 받아서 활용해야 한다
	// - 세션에서 정보를 받아야 한다(KakaoPayApproveReady)
	// - 인터셉터의 postHandle을 활용하면 쉽게 일괄 처리가 가능
	
	@GetMapping("/success")
	public String success(HttpSession session,
							@RequestParam String pg_token) throws URISyntaxException {
		
		// 세션에 있는 정보를 수신
		KakaoPayApproveReady ready = (KakaoPayApproveReady) session.getAttribute("KakaoPayApproveReady");
		
		// 세션에 저장된 정보 삭제
//		session.removeAttribute("KakaoPayApproveReady");

		// ready에 pg_token을 추가(3+1)
		ready.setPg_token(pg_token);
		
		// 서비스를 호출하여 승인 요청을 수행 후 결과를 받는다
		KakaoPayApproveResult result = kakaoPayService.approve(ready);
		
		// DB작업을 수행(필요 시)
		
		// 새로운 페이지로 리다이렉트(새로고침 시 다시 실행되는 것을 방지)
		return "redirect:finish";
	}
	
	// 결제 완료 페이지 매핑
	@GetMapping("/finish")
	public String finish() {
		return "pay/finish";
	}
	
	
	// 실패 처리 매핑
	// - 정보가 담신 세션 데이터를 삭제해야 함
	// - 인터셉터의 postHandle을 활용하면 쉽게 일괄 처리가 가능
	@GetMapping("/fail")
	public String fail() {
		return "pay/fail";
	}
	
	// 취소 처리 매핑
	// - 정보가 담신 세션 데이터를 삭제해야 함
	// - 인터셉터의 postHandle을 활용하면 쉽게 일괄 처리가 가능
	@GetMapping("/cancel")
	public String cancel() {
		return "pay/cancel";
	}
}