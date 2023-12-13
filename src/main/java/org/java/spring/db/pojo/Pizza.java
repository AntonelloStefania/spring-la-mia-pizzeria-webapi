package org.java.spring.db.pojo;



import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 32, nullable=false)
	@Length(min = 5, max = 32, message = "Name must be between 5 and 32 chars")
	@NotBlank(message="name cannot be only whitespaces or empty")
	private String name;
//	descrizione
	
	@Column(columnDefinition = "TEXT", length=100)
	@Length(min = 10, max = 100, message = "Description must be between 10 and 100 chars.")
	@NotBlank(message="name cannot be only whitespaces or empty")
	private String description;
//	foto (url)
	@URL(protocol = "https")
	@NotBlank(message="photo URL cannot be only whitespaces or empty")
	private String photo;

	//	prezzo
	@Column(nullable=false)
	@Positive (message="Price cannot be empty or negative")
	private double price;
	
	@OneToMany(mappedBy = "pizza")
	private List<Discount> discounts;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	public Pizza() { }
	public Pizza(String name,String description, String photo, double price, Ingredient...ingredients) { 
		setName(name);
		setDescription(description);
		setPhoto(photo);
		setPrice(price);
		setIngredients(ingredients);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Discount> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public void setIngredients(Ingredient...ingredients) {
		setIngredients(Arrays.asList(ingredients));
		
	}
	public void removeIngredients(Ingredient ingredient){
		getIngredients().remove(ingredient);
	}
	
	
}
