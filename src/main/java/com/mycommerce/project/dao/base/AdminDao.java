package com.mycommerce.project.dao.base;

import java.util.List;

import com.mycommerce.project.model.Admin;
import com.mycommerce.project.model.Category;
import com.mycommerce.project.model.User;

public interface AdminDao extends GenericDao<Admin, Long>{
	
	Admin findByName(String name);
}
