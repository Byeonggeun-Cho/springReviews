package com.kh.spring12;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring12.entity.City;
import com.kh.spring12.entity.CityWithZone;

import lombok.extern.slf4j.Slf4j;

// 목표: mybatis를 이용해서 개별 데이터를 불러온 뒤
//	    List<CityWithZone> 데이터를 만들기

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
@WebAppConfiguration	// 가상 web.xml 사용
public class Test01 {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		// 1. City를 조회 -> List<City>
		// 2. List<City>의 데이터 개수만큼 반복하며 각각의 City에 대한 Zone을 조회 -> List<Zone>
		// 3. 조회한 City와 List<Zone>을 이용해서 CityWithZone을 생성
		
		List<City> cityList = sqlSession.selectList("city.list");
		for(City city: cityList) {
			log.info("city = {}", city);
		}
		
		List<CityWithZone> cityList2 = sqlSession.selectList("city.list2");
		for(CityWithZone cityWithZone: cityList2) {
			log.info("city2 = {}", cityWithZone);
		}
	}

}
