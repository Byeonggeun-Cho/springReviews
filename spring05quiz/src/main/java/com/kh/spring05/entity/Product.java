package com.kh.spring05.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data		// setter + getter + toString
@NoArgsConstructor		// 기본생성자
@AllArgsConstructor		// 모든 인자를 갖는 생성자
@Builder				// 빌더 패턴을 사용할 수 있도록 내부클래스 구축
public class Product {
	private long no;
	private String name;
	private int price;
	private Date reg;
}
