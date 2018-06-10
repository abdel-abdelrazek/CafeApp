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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.general.NoSuchResourceException;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@Controller
@RequestMapping("control")
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("/allPersons")
	public String showProfileData(Model model) {
		model.addAttribute("persons", personService.getAll());
		return "persons";

	}

	@PostMapping("/product/{id}")
	public String editPRoduct(@PathVariable String id,@Valid Product product, BindingResult result) {

		String view = "redirect:/";

		if (!result.hasErrors()) {

			Product tmpProduct= 	productService.getProduct(Integer.parseInt(id));
			tmpProduct.setDescription(product.getDescription());
			tmpProduct.setPrice(product.getPrice());
			tmpProduct.setProductName(product.getProductName());
			tmpProduct.setProductType(product.getProductType());
			
			productService.save(product);
		} else {
			view = "addProduct";
		}
		return view;

	}

	@GetMapping("/product")
	public String Product(@RequestParam("id") String id,Model model) {
		
	 model.addAttribute("product", productService.getProduct(Integer.parseInt(id)));

	 return "addProduct";
	}
}
