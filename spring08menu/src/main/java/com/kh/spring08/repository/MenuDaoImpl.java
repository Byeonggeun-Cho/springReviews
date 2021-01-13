package com.kh.spring08.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring08.entity.Menu;

@Repository
public class MenuDaoImpl implements MenuDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int add(Menu menu) {
		
		int no = sqlSession.selectOne("menu.seq");
		menu.setNo(no);
		sqlSession.insert("menu.add", menu);
		
		return no;
	}
}
