package com.kh.springdi.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cat implements Pet{
	private String name;
	private int age;
	private String gender;
	
	@Override
	public String bark() {
		return "야옹~";
	}

}
