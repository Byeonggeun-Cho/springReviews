package com.kh.spring17.service;

import java.net.URISyntaxException;
import java.util.List;

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
	// 결제 요청정보 DB 조회 메소드
	public Payment get(int no) throws URISyntaxException;

	// 결제 승인 메소드
	public KakaoPayApproveResult approve(int no, String pg_token) throws URISyntaxException;
	public KakaoPayApproveResult approve(KakaoPayApproveReady ready) throws URISyntaxException;
	// 결제 승인정보 DB 갱신 메소드 
	public void approveDatabase(int no);

	// 결제 목록 호출 메소드
	public List<Payment> list();
	// 결제 조회 메소드(no)
	public KakaoPaySearchResult search(int no) throws URISyntaxException;
	// 결제 조회 메소드(tid)
	public KakaoPaySearchResult search(String tid) throws URISyntaxException;

	// 결제 취소 메소드
	public KakaoPayCancelResult cancel(int no) throws URISyntaxException;
	public KakaoPayCancelResult cancel(KakaoPayCancelReady ready) throws URISyntaxException;
	
	
	


}
