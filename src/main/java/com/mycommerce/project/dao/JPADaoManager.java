package com.mycommerce.project.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPADaoManager {

	private static JPADaoManager instance = null;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("mycommerce");
	private JpaProductDao productDao = new JpaProductDao();
	private JpaCategoryDao categoryDao = new JpaCategoryDao();
	private JpaUserDao userDao = new JpaUserDao();
	private JpaAdminDao adminDao = new JpaAdminDao();
	
	private JPADaoManager() {}
	
	public static JPADaoManager getInstence() {
		
		if (instance == null) {
			instance = new JPADaoManager();
			//créer instance + le EntityManagerFactory 
			//1fois
		}
		return instance;
	}
	
	public static void stop() {
		instance.emf.close();
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public JpaProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(JpaProductDao productDao) {
		this.productDao = productDao;
	}

	public JpaCategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(JpaCategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public JpaUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(JpaUserDao userDao) {
		this.userDao = userDao;
	}

	public JpaAdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(JpaAdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	
	
}
