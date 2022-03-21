package com.mycommerce.project.dao.base;

import java.util.List;

import com.mycommerce.project.model.User;

public interface UserDao extends GenericDao<User, Long>{

	User findByName(String name);
	
}