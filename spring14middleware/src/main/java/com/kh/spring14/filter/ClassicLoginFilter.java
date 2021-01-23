package com.kh.spring14.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * JSP/Servlet에서 쓰던 필터
 * - web.xml에 <filter>를 등록
 * - @WebFilter 사용
 * - Spring에 등록한 것이 아니기 때문에 @Autowired와 같은 스프링 기능 모두 사용 불가
 * 
 * [1] *가 마지막에 배치되는 경우(특정 영역을 검사)
 * [2] *가 첫번째에 배치되는 경우(확장자 검사)
 * [3] *를 사용하지 않는 경우(일일이 페이지 지정)
 */

@Slf4j
// @WebFilter(urlPatterns = "/member")
public class ClassicLoginFilter implements Filter{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Q1. sqlSession의 Autowired 가능 여부 -> 안됨
		log.info("sqlSession = {}", sqlSession);	// -> null로 나옴
		
		
		// 검사: session에 user가 있는지 없는지 검사
		// - ServletRequest는 HttpServletRequest의 상위 형태
		// - 필터는 Http에만 적용할 수 있는게 아니지 때문
		// - 다운 캐스팅하여 사용
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String user = (String) session.getAttribute("user");
		
		if(user != null) {		// 로그인 상태: 통과
			log.info("통과!");
			chain.doFilter(request, response);
		} else {		// 로그아웃 상태: 거절(redirect, error)
			log.info("거절!");
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(req.getContextPath());		// 루트("/") 페이지로 돌려보냄
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}













