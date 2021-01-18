package com.kh.spring11;


/**
 * 암호화(Encrypt) / 복호화(Decrypt) 
 * - 미지의 대상이 내가 관리하는 저옵에 접근해도 못알아보도록 변조/복원하는 과정
 * - 양방향 암호화 / 단방향 암호화
 * - 중요한 정보를 저장할 때에는 항상 유출되어도 문제가 발생하지 않는 방식을 선정(단방향)
 * - java 표준 라이브러리에도 암호화 알고리즘 라이브러리가 있음
 * - spring에서는 security라는 프레임워크를 만들어 웹 보안의 전반적인 처리 위임
 * - spring-security에 암호화 알고리즘이 구현되어 있음(Scrypt, Bcrypt, ...)
 */

public class Test01 {

}
