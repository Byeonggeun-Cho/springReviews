package com.kh.springmvc.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private long id;
	private String name;
	private int age;
	private String gender;
	private String address;
}
