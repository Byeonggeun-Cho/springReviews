package com.kh.spring15.repository;

import com.kh.spring15.entity.Cert;

public interface CertService {
	// 인증번호 검사 기능
	boolean check(Cert cert);
}
