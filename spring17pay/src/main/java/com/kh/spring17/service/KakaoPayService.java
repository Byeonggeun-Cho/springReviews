package com.kh.spring17.service;

import java.net.URISyntaxException;

import com.kh.spring17.entity.Payment;
import com.kh.spring17.vo.pay.KakaoPayApproveReady;
import com.kh.spring17.vo.pay.KakaoPayApproveResult;
import com.kh.spring17.vo.pay.KakaoPayCancelReady;
import com.kh.spring17.vo.pay.KakaoPayCancelResult;
import com.kh.spring17.vo.pay.KakaoPayRequestReady;
import com.kh.spring17.vo.pay.KakaoPayRequestResult;
import com.kh.spring17.vo.pay.KakaoPaySearchResult;

public interface KakaoPayService {
	// 결제 요청 메소드
	public KakaoPayRequestResult request(KakaoPayRequestReady ready) throws URISyntaxException;

	// 결제 승인 메소드
	public KakaoPayApproveResult approve(KakaoPayApproveReady ready) throws URISyntaxException;

	// 결제 조회 메소드
	public KakaoPaySearchResult search(String tid) throws URISyntaxException;

	// 결제 취소 메소드
	public KakaoPayCancelResult cancel(KakaoPayCancelReady ready) throws URISyntaxException;
	
	
	// 결제 정보 조회 메소드
	public Payment get(int no);
}
