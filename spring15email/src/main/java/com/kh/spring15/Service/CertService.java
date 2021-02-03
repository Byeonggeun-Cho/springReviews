package com.kh.spring15.Service;

import com.kh.spring15.entity.Cert;

public interface CertService {
	// 인증번호 생성 및 등록 기능
	String create(String who);
	
	// 인증번호 검사 기능
	boolean check(Cert cert);
	
	// 스케줄러 - (만료된)인증번호를 주기적으로 청소
	void clear();
}
