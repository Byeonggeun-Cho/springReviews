package com.kh.spring08.repository;

import com.kh.spring08.entity.Menu;

// 도구를 만들 때 추상화 구조를 갖도록 구성
// - 직접 연결이 아니라 중계하기 때문에 교체가 쉽고 각종 AOP 기능이 적용됨

// 책으로 치면 이 부분이 목차
public interface MenuDao {
	public int add(Menu menu);
}
