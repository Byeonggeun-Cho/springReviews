package com.kh.spring15.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring15.entity.Cert;
import com.kh.spring15.repository.CertDao;

@Service
public class CertServiceImpl implements CertService {
	@Autowired
	private RandomService randomService;
	
	@Override
	public String create(String who) {
		// 1. 인증번호 객체 생성
//		Cert cert = Cert.builder().who(id).what("123123").build();
		String number = randomService.generateNumber();	// 랜덤
		Cert cert = Cert.builder().who(who).what(number).build();
		
		// 2. DB 등록 - sqlSession / CertDao
		certDao.add(cert);
		
		return number;
	}
	
	@Autowired
	private CertDao certDao;
	
	// 검사와 삭제 기능 합쳐서 구현
	@Override
	public boolean check(Cert cert) {
		if(certDao.check(cert)) {	// 인증 성공
			certDao.remove(cert);
			return true;
		}
		
		return false;
	}

}
