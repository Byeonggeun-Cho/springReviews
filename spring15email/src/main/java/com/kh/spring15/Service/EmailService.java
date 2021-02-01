package com.kh.spring15.Service;

// 이메일 서비스에 대항 요구사항
public interface EmailService {
	// 인증번호 발송
	// - 준비물: 인증번호를 받을 사람의 이메일(어디서 얻을 것인가?)
	//		- 아이디를 이용하여 사용자의 이메일을 조회한 뒤 이용
	//		- 아이디가 이메일 형식인 경우  <-- 이 경우로 가정하여 진행
	void sendCertification(String id);
}
