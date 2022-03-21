package com.mycommerce.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("user")
public class User extends Person implements Serializable{

	@ManyToMany (mappedBy = "inBasket", fetch = FetchType.EAGER)
	private List<Product> basket = new ArrayList<Product>();

	public User() {
	}
	
	public User(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public List<Product> getBasket() {
		return basket;
	}

	public void setBasket(List<Product> basket) {
		this.basket = basket;
	}
	
	public void addBasket(Product p) {
		this.basket.add(p);
	}
	
	public void removeBasket(int index) {
		this.basket.remove(index);
	}
}
