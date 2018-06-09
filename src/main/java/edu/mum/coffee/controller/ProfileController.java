package edu.mum.coffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.general.NoSuchResourceException;


@Controller

public class ProfileController {


	@GetMapping("/profile")
	public String showAccessDenied() {
		
		return "profileData";
		
	}
}
