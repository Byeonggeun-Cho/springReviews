package com.kh.spring17.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring17.entity.Payment;
import com.kh.spring17.service.KakaoPayService;
import com.kh.spring17.vo.pay.KakaoPayApproveResult;
import com.kh.spring17.vo.pay.KakaoPayCancelReady;
import com.kh.spring17.vo.pay.KakaoPayCancelResult;
import com.kh.spring17.vo.pay.KakaoPayRequestReady;
import com.kh.spring17.vo.pay.KakaoPayRequestResult;
import com.kh.spring17.vo.pay.KakaoPaySearchResult;

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
	public String confirm(HttpSession session,
							@ModelAttribute KakaoPayRequestReady ready) throws URISyntaxException {
		// Test03에서 수행한 작업을 그대로 구현
		
		// 정보 추가(3+2)
		ready.setPartner_order_id(UUID.randomUUID().toString());
		ready.setPartner_user_id(UUID.randomUUID().toString());
		
		// 서비스 호출
		// 결제사에 결제요청 후 응답
		KakaoPayRequestResult result = kakaoPayService.request(ready);

//		log.info("partner_order_id={}", ready.getPartner_order_id());
//		log.info("partner_user_id={}", ready.getPartner_user_id());
//		log.info("tid={}", result.getTid());

		
		// DB 사용 -> 세션 사용 안 함
		// 세션에 결제 승인을 위해서 필요한 정보를 추가
//		session.setAttribute("KakaoPayApproveReady",
//				KakaoPayApproveReady.builder()
//					.partner_order_id(ready.getPartner_order_id())
//					.partner_user_id(ready.getPartner_user_id())
//					.tid(result.getTid())
//					.build());
		
		// 사용자에게 카카오페이지 결제화면으로 리다이렉트할 것을 지시
		return "redirect:" + result.getNext_redirect_pc_url();
	}
	
	// 성공 처리 매핑
	// - Kakao API에서 pg_token이라는 파라미터를 보내기 때문에 받아서 활용해야 한다
	// - 세션에서 정보를 받아야 한다(KakaoPayApproveReady)
	// - 인터셉터의 postHandle을 활용하면 쉽게 일괄 처리가 가능
	//		-> DB 사용 -> 세션 사용 안 함
	@GetMapping("/success")
	public String success(
							// HttpSession session,	//		-> DB 사용 -> 세션 사용 안 함
							@RequestParam String pg_token,
							@RequestParam int no) throws URISyntaxException {
		
		// 코드 정리
		// no와 pg_token을 건네주고 KakaoPayApproveResult를 받도록 호출
		KakaoPayApproveResult result = kakaoPayService.approve(no, pg_token);
		
		// 새로운 페이지로 리다이렉트(새로고침 시 다시 실행되는 것을 방지)
		return "redirect:search?tid="+result.getTid();
	}
	
	// 결제 완료 페이지 매핑
	@GetMapping("/finish")
	public String finish() {
		return "pay/finish";
	}
	
	
	// 실패 처리 매핑
	// - 정보가 담신 세션 데이터를 삭제해야 함
	// - 인터셉터의 postHandle을 활용하면 쉽게 일괄 처리가 가능
	//		-> DB 사용 -> 세션 사용 안 함
	@GetMapping("/fail")
	public String fail(@RequestParam int no) {
		return "pay/fail";
	}
	
	// 취소 처리 매핑
	// - 정보가 담신 세션 데이터를 삭제해야 함
	// - 인터셉터의 postHandle을 활용하면 쉽게 일괄 처리가 가능
	//		-> DB 사용 -> 세션 사용 안 함
	@GetMapping("/cancel")
	public String cancel(@RequestParam int no) {
		return "pay/cancel";
	}
	
	
	// 전체 결제내역 조회 매핑
	// - 회원은 자신의 내역만 볼 수 있게 구현
	// - 가맹점주는 자신이 판매한 내역만 볼 수 있게 구현
	// - 추가적인 장치가 실제 구현에서 필요
	@GetMapping("/list")
	public String list(Model model) {
		
		List<Payment> list = kakaoPayService.list();
		model.addAttribute("list", list);
		
		return "pay/list";
	}
	
	
	
	// 조회 처리 매핑
	// - tid를 제공받아 카카오페이 API를 사용해 정보를 조회하고
	// 		-> no를 제공받아 DB조회 후 tid를 찾아 카카오페이 API를 사용해 정보 호출
	// - view 페이지에 해당 내용을 전달(편집 또는 전체 전달)
	@GetMapping("/search")
	public String search(@RequestParam int no,
						// @RequestParam String tid,
						Model model) throws URISyntaxException {
		
		KakaoPaySearchResult result = kakaoPayService.search(no);
		// KakaoPaySearchResult result = kakaoPayService.search(tid);
		
		model.addAttribute("result", result);
		
		return "pay/search";
	}
	
	// 삭제 처리 매핑
	// - tid와 cancel_amount를 전달받아 진행
	// - 실제로는 tid를 이용해서 금액을 DB에서 조회한 뒤 cancel_amount로 설정하여 구현
	// 		-> 실제로는 no를 이용해서 금액을 DB에서 조회한 뒤 cancel_amount로 설정하여 구현
	@GetMapping("/undo")
	public String undo( @RequestParam int no
						// @RequestParam String tid,
						// @RequestParam int cancel_amount
						) throws URISyntaxException {
		
		// no를 주고 취소시킨 뒤 결과(KakaoPayCancelResult)를 받아 진행
		KakaoPayCancelResult result = kakaoPayService.cancel(no);
		
		return "redirect:search?tid" + result.getTid();
	}
	
}