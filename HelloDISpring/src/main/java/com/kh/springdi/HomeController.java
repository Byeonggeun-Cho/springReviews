package com.kh.springdi;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.springdi.owner.Owner;
import com.kh.springdi.pet.Cat;
import com.kh.springdi.pet.Dog;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		Owner owner = Owner.builder()
						.name("주인")
						.age(20)
						.gender("여")
						.pet(new Dog("돌돌이", 2, "숫컷"))
						.build();
		
		System.out.println(owner.getPet().bark());
		
		owner.setPet(new Cat("나비", 2, "암컷"));
		
		System.out.println(owner.getPet().bark());
		
		return "home";
	}
	
}
