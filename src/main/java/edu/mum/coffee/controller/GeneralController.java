package edu.mum.coffee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.general.NoSuchResourceException;


@Controller
public class GeneralController {

	
	@ExceptionHandler(value = NoSuchResourceException.class)
	public ModelAndView handle(Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("e", e);
		mv.setViewName("noSuchResource");
		return mv;
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
	
	@GetMapping("*")
	public String exceptionHandeler() {
		
		return "exceptionHandeling";
		
	}
	
	@GetMapping("/rest")
	public String testRest(Model moedel) {
		
		RestTemplate restTemplate = new RestTemplate();
		String products
		  = "http://localhost:8090/product/all";
		ResponseEntity<Product> response
		  = restTemplate.getForEntity(products , Product.class);
		
		moedel.addAttribute("products", response);
		
		return "restTest";
		
	}
}
