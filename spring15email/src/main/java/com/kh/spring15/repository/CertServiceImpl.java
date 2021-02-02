package com.kh.spring15.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring15.entity.Cert;

@Service
public class CertServiceImpl implements CertService {
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
