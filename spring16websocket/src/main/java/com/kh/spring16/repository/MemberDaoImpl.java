package com.kh.spring16.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.spring16.vo.Member;

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

	@Override
	public Member login(Member member) {
		// member에는 id와 pw가 존재
		// -> 비교를 할 때 데이터베이스나 자바를 이용해서 직접 비교 불가
		//    (암호화되어 저장 된 값과 비교해야 한다)
		// -> encoder.matches()를 사용
		
		Member find = sqlSession.selectOne("member.login", member);
		
		if(encoder.matches(member.getPw(), find.getPw())) {	// 로그인 성공
			return find;
			
		} else {	// 로그인 실패
			return null;
		}
	}
}
