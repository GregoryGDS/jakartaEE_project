package com.mycommerce.project.dao;

import java.util.ArrayList;
import java.util.List;

import com.mycommerce.project.dao.base.ProductDao;
import com.mycommerce.project.model.Category;
import com.mycommerce.project.model.Product;
import com.mycommerce.project.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JpaProductDao implements ProductDao{

	@Override
	public void add(Product object) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			
			em.persist(object);
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void update(Product object) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			
			em.merge(object);
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}		
	}

	@Override
	public Product findById(Long id) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		Product prod = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			Query query = em.createQuery("from Product where id = :id");
			query.setParameter("id", id);

			prod = (Product) query.getSingleResult();
						
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}

		return prod;
	}

	@Override
	public List<Product> getAll() {
		List<Product> listProd = new ArrayList<>();
		EntityManager em = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();

			Query query = em.createQuery("from Product");
			// Query query = em.createQuery("select p from Product p");

			listProd = (List<Product>) query.getResultList();
			
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
		}finally {
			try {
				em.close();
			} catch (Exception e) {
				System.out.println("Erreur close :" + e.getMessage());
			}

		}
		return listProd;
	}

	@Override
	public void remove(Product var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		Product remove = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			
			remove = em.merge(findById(id));
	
			em.remove(remove);
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}			
	}
}
