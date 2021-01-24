package com.kh.spring14.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 인터셉터(Interceptor)
 * - 요청을 중간에 가로채서 간섭하거나 감시하는 도구
 * - 총 세 가지 시점에 대해 간섭 가능
 * 	1. 컨트롤러 실행 전(preHandle)
 * 	2. 컨트롤러 실행 후(PostHandle)
 * 	3. 뷰 렌더링 후(afterCompletion)
 * 
 * - DispatcherServlet을 간섭하는 것이므로 servlet-context.xml에 등록
 * - 인터셉터 설정도 servlet-context.xml에 수행
 */

@Slf4j
public class SpringLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("preHandle 실행");
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		log.info("postHandle 실행");
		
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		log.info("afterCompletion 실행");
		
		super.afterCompletion(request, response, handler, ex);
	}
// public class SpringLoginInterceptor implements HandlerInterceptor{

	
	
}
