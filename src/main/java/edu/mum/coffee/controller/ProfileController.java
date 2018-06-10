package edu.mum.coffee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.general.NoSuchResourceException;
import edu.mum.coffee.service.PersonService;

@Controller

public class ProfileController {

	@Autowired
	PersonService personService;

	@GetMapping("/profile")
	public String showProfileData(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();

		String userName = user.getUsername();
		model.addAttribute("person", personService.findByEmail(userName).get(0));
		return "profileData";

	}

	@PostMapping("/profile")
	public String saveProfileData(@Valid Person person, BindingResult result) {

		String view = "redirect:/profile";

		if (!result.hasErrors()) {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = (User) authentication.getPrincipal();

			String userName = user.getUsername();
			Person tmpPerson = personService.findByEmail(userName).get(0);
			tmpPerson.setEmail(person.getEmail());
			tmpPerson.setFirstName(person.getFirstName());
			tmpPerson.setLastName(person.getLastName());
			tmpPerson.setPhone(person.getPhone());
	
			personService.savePerson(tmpPerson);
		} else {
			view = "/profile";
		}
		return view;

	}
}
