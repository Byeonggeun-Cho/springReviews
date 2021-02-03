package com.kh.spring15.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring15.entity.Cert;

@Repository
public class CertDaoImpl implements CertDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void add(Cert cert) {
		sqlSession.insert("cert.add", cert);
	}

	@Override
	public boolean check(Cert cert) {
		// 인증번호가 생성된지 5분 이내에만 인증 가능
		int count = sqlSession.selectOne("cert.checkWithTimeLimit", cert);

//		if(count == 1) {
//			return true;
//		} else {
//			return false;
//		}
		
		return count == 1;
	}

	@Override
	public void remove(Cert cert) {
		sqlSession.delete("cert.remove", cert);
	}
	
	@Override
	public void clear() {
		sqlSession.delete("cert.clear");
	}
}