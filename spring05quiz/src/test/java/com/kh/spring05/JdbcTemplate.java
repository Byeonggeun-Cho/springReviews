package com.kh.spring05;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class JdbcTemplate {

	public static SqlSessionFactory getSqlSessionFactory() throws IOException {
		// SqlSessionFactory, SqlSession 생성
		// [1] 설정파일 로딩
		// [2] SqlSessionFactory 생성
		// [3] SqlSession 생성
		
		InputStream in = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		
		return factory;
	}
	
}
