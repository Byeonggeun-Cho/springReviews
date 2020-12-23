package com.kh.springdi;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.springdi.human.Human;
import com.kh.springdi.owner.Owner;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired		// getBean()을 자동으로 수행
	private Human human;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		// Spring ApplicationContext를 사용하기 전
//		Owner owner = Owner.builder()
//						.name("주인")
//						.age(20)
//						.gender("여")
//						.pet(new Dog("돌돌이", 2, "숫컷"))
//						.build();
//		
//		System.out.println(owner.getPet().bark());
//		
//		owner.setPet(new Cat("나비", 2, "암컷"));
//		
//		System.out.println(owner.getPet().bark());
		
		
		// Spring ApplicationContext를 사용할 때
		ApplicationContext context = new GenericXmlApplicationContext("owner-context.xml");
		
//		Owner owner = (Owner) context.getBean("owner");
		Owner owner = context.getBean("owner", Owner.class);
		
		System.out.println(owner);
		System.out.println(owner.getPet().bark());
		
		
		// Annotation을 사용한 bean 생성
		
		System.out.println(human);
		System.out.println(human.getJob().Work());
		
		return "home";
	}
	
}
