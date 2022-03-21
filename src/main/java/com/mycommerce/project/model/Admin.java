package com.mycommerce.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.CategoryDao;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Person implements Serializable{

	public Admin() {
    }
	
    public Admin(String name) {
        super(name,true);
    }

    public void validAccount(Person p){
        p.setIsValid(true);
    }

    public void addCategory(Category c){
        CategoryDao categoryDao = DaoFactory.getCategoryDao();
        categoryDao.add(c);
    }

    public void removeCategory(Category c){
        CategoryDao categoryDao = DaoFactory.getCategoryDao();
        categoryDao.remove(c);
    }

    public void editCategory(Category c){
        CategoryDao categoryDao = DaoFactory.getCategoryDao();
        categoryDao.update(c);
    }
}
