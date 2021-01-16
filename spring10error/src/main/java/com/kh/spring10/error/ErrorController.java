package com.kh.spring10.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 컨트롤러는 아니지만 컨트롤러를 감시(Advice)하며 유사한 역할을 수행
 *
 */
// @ControllerAdvice(basePackage = "com.kh.spring10.controller")			// basePackage 지정된 패키지 내 모든 대상 감시
// @ControllerAdvice(basePackageClasses = {TestController.class})			// 특정 이름을 가진 컨트롤러를 선택하여 감시
@ControllerAdvice(annotations = {Controller.class, RestController.class})	// 컨트롤러 어노테이션을 가진 대상을 감시
@Slf4j
public class ErrorController {

	/**
	 * 실제 오류가 발생 시 수행될 매핑과 유사한 형태의 메소드 = ExceptionHandler
	 */
	
	@ExceptionHandler(ArithmeticException.class)
	public String first() {
		
		// logger를 이용하여 오류 기록을 남김
		log.error("오류 발생");
		
		// viewResolver 적용 됨
		return "error/first";
	}
	
}
