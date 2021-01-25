package com.kh.spring14.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring에서 제공하는 필터 사용법
 * - Filter는 Servlet/JSP 규약 상 무조건 web.xml에 등록하도록 되어있다.
 * - spring에 등록해봤자 필터로서의 효과가 발휘되지 않는다.
 * 		- DispatcherServlet의 통제 밖 영역에 대한 filter 처리를 포함하기 위해
 *
 * - 상속은 동일하게 받지만 @WebFilter가 아니라 Spring bean으로 등록해야 한다.
 */

@Slf4j
@Component		// 도구로 사용
public class SpringLoginFilter implements Filter{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Q1. sqlSession의 Autowired 가능 여부 -> 가능
		log.info("sqlSession = {}", sqlSession);
		
		
		// 검사: session에 user가 있는지 없는지 검사
		// - ServletRequest는 HttpServletRequest의 상위 형태
		// - 필터는 Http에만 적용할 수 있는게 아니지 때문
		// - 다운 캐스팅하여 사용
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String user = String.valueOf(session.getAttribute("user"));
		
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
