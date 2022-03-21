package com.mycommerce.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String content;
    private float price;
    
    @ManyToOne
    private Category category;
    
    @ManyToMany
    @JoinTable(
    		name="panier",
    		joinColumns = {@JoinColumn(name="product_id")},
    		inverseJoinColumns = {@JoinColumn(name="user_id")}
    )
	private List<User> inBasket = new ArrayList<User>();

    public Product() {
    }

    public Product(String name, String content, float price, Category category) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<User> getInBasket() {
		return inBasket;
	}
    
	public void addInBasket(User u) {
		this.inBasket.add(u);
	}
	
	public void removeInBasket(Integer index) {
		this.inBasket.remove(index);
	}
}
