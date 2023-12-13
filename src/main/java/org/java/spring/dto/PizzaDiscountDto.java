package org.java.spring.dto;

import java.time.LocalDate;

public class PizzaDiscountDto {
	
	private int pizza_id;
	private String name;
	private LocalDate start_date;
	private LocalDate end_date;
	
	public int getPizza_id() {
		return pizza_id;
	}
	public void setPizza_id(int pizza_id) {
		this.pizza_id = pizza_id;
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
	

	
	
}
