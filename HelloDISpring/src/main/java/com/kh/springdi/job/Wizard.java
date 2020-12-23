package com.kh.springdi.job;

import org.springframework.stereotype.Component;

@Component("wizard")
public class Wizard implements Job {
	@Override
	public String Work() {
		return "대지의 기원~";
	}

}
