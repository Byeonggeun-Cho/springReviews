package com.kh.springdi.human;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.springdi.job.Job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Human {
	
	@Value("가나다")
	private String name;
	
	@Value("36")
	private int age;
	
	@Value("여자")
	private String gender;

//	@Autowired
//	@Qualifier("wizard")
//	private Job job;
	
	private Job job;
	
	@Autowired
	public Human(@Qualifier("wizard") Job job) {
		super();
		this.job = job;
	}
	
}
