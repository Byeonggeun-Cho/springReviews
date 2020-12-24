package com.kh.springmvc.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kh.springmvc.member.vo.Member;

@Repository
public class MemberDao {
	public List<Member> findAll(){
		return createMemberList();
	}

	// 테스트용 멤버를 생성해주는 임시 메소드
	private List<Member> createMemberList() {
		List<Member> list = new ArrayList<Member>();
		
		for(int i=1; i <= 15; i++) {
			list.add(new Member((long)i, "아무개" + i, 30 + i, "남자", "서울시 강남구"));
		}
		
		return list;
	}
	
}
