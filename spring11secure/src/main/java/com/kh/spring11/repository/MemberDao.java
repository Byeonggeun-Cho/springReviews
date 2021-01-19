package com.kh.spring11.repository;

import com.kh.spring11.entity.Member;

public interface MemberDao {

	// 회원가입
	public void join(Member member);
	
	// 로그인
//	boolean login(Member member);
	Member login(Member member);
}
