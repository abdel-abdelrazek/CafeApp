package edu.mum.coffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		return "login";
		
	}
}
