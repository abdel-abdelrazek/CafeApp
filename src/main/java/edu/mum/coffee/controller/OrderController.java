package edu.mum.coffee.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;
	@Autowired
	PersonService personService;

	@GetMapping({ "/doOrder" })
	public String createOrderPage(Model model) {

		model.addAttribute("prods", productService.getAllProduct());

		return "createOrder";
	}

	@GetMapping({ "/details" })
	public String orderDetails(String id, Model model) {

		model.addAttribute("productId", id);
		return "orderDetails";
	}

	@PostMapping({ "/create" })
	public String createOrder(@RequestParam("quantity") String quantity, @RequestParam("productId") String id,
			Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();

		String userName = user.getUsername();

		Order order = new Order();
		Date dt = new Date();
		order.setOrderDate(dt);

		order.setPerson(personService.findByEmail(userName).get(0));
		orderService.save(order);

		return "index";
	}

}
