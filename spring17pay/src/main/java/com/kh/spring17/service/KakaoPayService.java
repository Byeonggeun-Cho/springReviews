package com.kh.spring17.service;

import java.net.URISyntaxException;

import com.kh.spring17.vo.pay.KakaoPayApproveReady;
import com.kh.spring17.vo.pay.KakaoPayApproveResult;
import com.kh.spring17.vo.pay.KakaoPayRequestReady;
import com.kh.spring17.vo.pay.KakaoPayRequestResult;

public interface KakaoPayService {
	// 결제 요청 메소드
	public KakaoPayRequestResult request(KakaoPayRequestReady ready) throws URISyntaxException;

	// 결제 승인 메소드
	public KakaoPayApproveResult approve(KakaoPayApproveReady ready) throws URISyntaxException;

}
