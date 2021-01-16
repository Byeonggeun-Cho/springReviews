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
	 * - 예외의 종류를 지정해야 한다
	 * - 모아서 처리하고 싶다면 Exception(예외 최상위) 또는 Throwable(예외 + 에러 최상위: Object 상속)
	 * 		- 내가 만든 코드를 내가 확인? Exception
	 *  	- 남이 만든 코드를 내가 확인? Throwable
	 * 		- Error : 컴파일 시 문법적인 오류와 런타임 시 널포인트 참조와 같은 오류로 프로세스에 심각한 문제를 야기 시켜 프로세스를 종료 시킬 수 있다.
	 * 		- Exception : 컴퓨터 시스템의 동작 도중 예기치 않았던 이상 상태가 발생하여 수행 중인 프로그램이 영향을 받는 것. 예를 들면, 연산 도중 넘침에 의해 발생한 끼어들기 등이 이에 해당한다.
	 * - ExceptionHandler는 존재 이유가 예외처리이기 때문에 예외 객체를 사용할 수 있다.
	 * - 그 외 @RequestMapping에서 사용하는 모든 도구 사용 가능(ex: HttpSession 등)
	 */
	
	// 처리할 예외 클래스를 지정
	@ExceptionHandler(ArithmeticException.class)
	public String first() {
		
		// logger를 이용하여 오류 기록을 남김
		log.error("오류 발생");
		
		// viewResolver 적용 됨
		return "error/first";
	}
	
	@ExceptionHandler(Exception.class)
	public String second(Exception e) {
		
		log.error("오류 발생: ", e);		// e.printStackTrace()와 유사
		
		return "error/second";
	}
	
}
