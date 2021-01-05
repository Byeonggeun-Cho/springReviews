package com.kh.spring02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *	연동 테스트
 *	- 연동을 하려면 추가정보 필요
 *		- 어떤 환경에서 테스트 할 것인지? @RunWith
 *		- 연동시킬 설정파일의 위치? @ContextConfiguration
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
				"file:src/main/webapp/WEB-INF/spring/root-context.xml",
//				"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		}
)
public class Test02 {
	
	/**
	 * 환경이 연동되어 있다면 필요한 도구들을 자동으로 연동시켜 사욯할 수 있다.
	 * @Autowired
	 */

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void test() {
		System.out.println(jdbcTemplate);
		
	}
	
	
}
