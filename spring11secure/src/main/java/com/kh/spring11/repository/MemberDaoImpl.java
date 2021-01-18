package com.kh.spring11.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.spring11.entity.Member;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void join(Member member) {

//		log.info("encoder = {}", encoder);
		
		// 암호화
		String password = member.getPw();
		String result = encoder.encode(password);
		member.setPw(result);
		
		// 등록
		sqlSession.insert("member.join", member); 
	}
}
