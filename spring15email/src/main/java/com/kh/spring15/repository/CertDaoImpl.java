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
}