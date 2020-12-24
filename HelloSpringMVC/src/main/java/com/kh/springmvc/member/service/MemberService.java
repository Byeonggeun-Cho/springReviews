package com.kh.springmvc.member.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springmvc.member.dao.MemberDao;
import com.kh.springmvc.member.vo.Member;

@Service
public class MemberService {

	@Autowired
	private MemberDao dao;
	
	public List<Member> findMembers(int pageNum, int size) {
		int startNum = (pageNum > 1) ? ((pageNum-1) * size + 1) : pageNum;
		int lastNum = startNum + (size - 1);
		
		List<Member> members = dao.findAll();
		System.out.println(members);
		
		return members
				.stream()
				.filter(member -> member.getId() >= startNum && member.getId() <= lastNum)
				.collect(Collectors.toList());
	}

	public Member findById(long id) {

		List<Member> members = dao.findAll();
		
		return members
				.stream()
				.filter(member -> member.getId() == id)
				.findFirst()
				.orElse(null);
	}

}
