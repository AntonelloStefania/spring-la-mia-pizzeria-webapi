package org.java.spring.db.pojo;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Length(min=3, max = 64)
	private String name;
	
	private LocalDate start_date;
	private LocalDate end_date;
	
	@ManyToOne
	private Pizza pizza;
	
	public Discount(){ }
	
	public Discount(String name, LocalDate start_date, LocalDate end_date, Pizza pizza) {
		setName(name);
		setStart_date(start_date);
		setEnd_date(end_date);
		setPizza(pizza);
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

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	
}
