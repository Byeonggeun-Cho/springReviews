package com.kh.spring15.repository;

import com.kh.spring15.entity.Cert;

public interface CertDao {
	// 등록 기능
	void add(Cert cert);
	
	// 검사 기능
	boolean check(Cert cert);
	
	// 삭제 기능
	void remove(Cert cert);
	
	// 청소 기능
	void clear();
}
