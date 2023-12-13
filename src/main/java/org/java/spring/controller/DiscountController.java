package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.DiscountService;
import org.java.spring.db.serv.PizzaService;
import org.java.spring.dto.PizzaDiscountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DiscountController {
	
	@Autowired
	private PizzaService pizzaseService;
	
	@Autowired
	private DiscountService discountService;
	
	@GetMapping("pizza/{id}/discount/create")
	public String getDiscountForm(Model model,
				@PathVariable int id) {
		Pizza pizza = pizzaseService.findById(id);
		Discount discount = new Discount();
		List<Discount> discounts = pizza.getDiscounts();
	
		model.addAttribute("pizza",pizza);
		model.addAttribute("discounts", discounts);
		model.addAttribute("discount", discount);
		return "discount-form";
	}
	
	@PostMapping("/pizza/{id}/discount/create")
	public String storeDiscount(@ModelAttribute PizzaDiscountDto pizzaDiscountDto,
			@PathVariable int id) {
		Pizza pizza = pizzaseService.findById(id);
		
		Discount discount = new Discount(pizzaDiscountDto.getName(),
							pizzaDiscountDto.getStart_date(),
							pizzaDiscountDto.getEnd_date(),
							pizza
							);
		
		discountService.saveDiscount(discount);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/{id}/discount/{discount_id}")
	public String editDiscount(Model model, @PathVariable int id, @PathVariable int discount_id) {
		Pizza pizza= pizzaseService.findById(id);
		Discount discount = discountService.findById(discount_id);
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("discount", discount);
		
		return "discount-form";
	}
	
	
	@PostMapping("/pizza/{id}/discount/{discount_id}")
	public String updateDiscount(@ModelAttribute PizzaDiscountDto pizzaDiscountDto, @PathVariable int id, @PathVariable int discount_id) {
		Pizza pizza= pizzaseService.findById(id);
		Discount discount = discountService.findById(discount_id);
		
		discount.setName(pizzaDiscountDto.getName());
		discount.setStart_date(pizzaDiscountDto.getStart_date());
		discount.setEnd_date(pizzaDiscountDto.getEnd_date());
		
		discountService.saveDiscount(discount);
		return "redirect:/";
	}

}
