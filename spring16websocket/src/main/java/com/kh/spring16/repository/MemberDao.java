package com.kh.spring16.repository;

import com.kh.spring16.vo.Member;

public interface MemberDao {

	// 회원가입
	public void join(Member member);
	
	// 로그인
//	boolean login(Member member);
	Member login(Member member);
}
