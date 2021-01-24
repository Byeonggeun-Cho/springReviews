package com.kh.spring14.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 인터셉터(Interceptor)
 * - 요청을 중간에 가로채서 간섭하거나 감시하는 도구
 * - 총 세 가지 시점에 대해 간섭 가능
 * 	1. 컨트롤러 실행 전(preHandle)
 * 		- request: 사용자 요청 정보
 * 		- response: 서버 응답 정보
 * 		- handler: 실행될 대상 정보(일반적으로 컨트롤러)
 * 	2. 컨트롤러 실행 후(PostHandle)
 * 		- request: 사용자 요청 정보
 * 		- response: 서버 응답 정보
 * 		- handler: 실행될 대상 정보(일반적으로 컨트롤러)
 * 		- ModelAndView: view 페이지 정보, 전달되는 model 정보
 * 	3. 뷰 렌더링 후(afterCompletion)
 * 		- request: 사용자 요청 정보
 * 		- response: 서버 응답 정보
 * 		- handler: 실행될 대상 정보(일반적으로 컨트롤러)
 * 		- ex: 실행 중에 DispatcherServlet에서 발생하는 예외 중 처리되지 않은 예외정보가 담긴다.
 * 			-> ControllerAdvice에서 처리하는 것을 권장
 * 
 * - DispatcherServlet을 간섭하는 것이므로 servlet-context.xml에 등록
 * - 인터셉터 설정도 servlet-context.xml에 수행
 */

@Slf4j
public class SpringLoginInterceptor extends HandlerInterceptorAdapter {

	// interceptor는 spring bean이므로 자동으로 스프링의 모든 기능 사용이 가능하다
	
	long start;		// 프로그램 시작시간
	long finish;	// 프로그램 종료시간
	
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 시작 시각
		start = System.currentTimeMillis();
		

		log.info("preHandle 실행");
		log.info("handler = {}, {}", handler, handler.getClass());
		
		// 통과/거절 처리는 어떻게 하는가?
		// 통과: return true;
		// 거절: return false;
		//	-> 서버에서 더이상 과정이 진행되지 않으므로 사용자 응답 방식을 지정
		//	-> 리다이렉트 or 에러 송출 등의 추가 작업이 필요

		// root 페이지로 리다이렉트
//		response.sendRedirect(request.getContextPath());

		// 에러 번호에 해당사는 메시지 송출
//		response.sendError(404);
		
		String user = (String) request.getSession().getAttribute("user");
		
		if(user != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
//		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		log.info("postHandle 실행");
		log.info("handler = {}", handler);
		log.info("ModelAndview = {}", modelAndView);
		
		
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		log.info("afterCompletion 실행");
		log.info("handler = {}", handler);
		log.info("ex = {}", ex);
		
		
		// 종료 시각
		finish = System.currentTimeMillis();
		
		log.info("소요시간: {}초", ((double)(finish - start))/1000);
		
		super.afterCompletion(request, response, handler, ex);
	}
// public class SpringLoginInterceptor implements HandlerInterceptor{
	
}
