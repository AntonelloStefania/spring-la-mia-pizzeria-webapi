package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/ingredienti")
	public String getIngredients(Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		return "ingredients";
	}
	
	@GetMapping("/ingredienti/create")
	public String IngredientsCreate(Model model) {
		Ingredient ingredient = new Ingredient();
		model.addAttribute("ingredient", ingredient);
		return "ingredient-form";
	}
	
	@PostMapping("/ingredienti/create")
	public String IngredientsStore(Model model,
			@Valid @ModelAttribute Ingredient ingredient,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "ingredient-form";
		}
		ingredientService.save(ingredient);
		
		return "redirect:/ingredienti";
		
	}
	
	@PostMapping("/ingredienti/delete/{id}")
	public String IngredientDelete(@PathVariable int id) {
		Ingredient ingredient = ingredientService.findById(id);
		
		
		for(Pizza pizza : ingredient.getPizzas()) {
			pizza.removeIngredients(ingredient);
		}
						
		ingredientService.delete(ingredient);
		
		return "redirect:/ingredienti";
	}
	
}
