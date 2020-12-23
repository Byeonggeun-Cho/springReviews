package com.kh.springdi.job;

import org.springframework.stereotype.Component;

@Component("warrior")
public class Warrior implements Job {
	@Override
	public String Work() {
		return "용 잡으러 간다~";
	}

}
