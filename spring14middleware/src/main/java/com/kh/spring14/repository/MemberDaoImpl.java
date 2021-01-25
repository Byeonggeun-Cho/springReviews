package com.kh.spring14.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.spring14.entity.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public Member login(Member member) {
		
		Member find = sqlSession.selectOne("member.login", member);
		// 비밀번호 비교
		if(encoder.matches(member.getPw(), find.getPw())) {		// 성공
			return find;
		} else {		// 실패
			return null;
		}
	}

}
