package com.kh.spring12;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring12.entity.CityWithZone;
import com.kh.spring12.entity.Zone;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
@WebAppConfiguration	// 가상 web.xml 사용
public class Test03 {

	@Autowired
	private SqlSession sqlSession;

	@Test
	public void test() {
		
		String city_name = "서울시";
		
		List<CityWithZone> list = sqlSession.selectList("city.search", city_name);
		
		for(CityWithZone city: list) {
			log.info("city = {}", city);
			
			for(Zone zone: city.getList()) {
				log.info("zone = {}", zone);
			}
		}
		
	}
}
