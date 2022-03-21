package com.mycommerce.project.dao;

import java.util.ArrayList;
import java.util.List;

import com.mycommerce.project.dao.base.CategoryDao;
import com.mycommerce.project.model.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JpaCategoryDao implements CategoryDao{

	@Override
	public void add(Category object) {
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
	public void update(Category object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category findById(Long id) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		Category cat = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			Query query = em.createQuery("from Category where id = :id");
			query.setParameter("id", id);

			cat = (Category) query.getSingleResult();
						
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}

		return cat;
	}

	@Override
	public List<Category> getAll() {
		List<Category>  listCat = new ArrayList<>();
		EntityManager em = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();

			Query query = em.createQuery("from Category");
			
			listCat = (List<Category>) query.getResultList();
			
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
		}finally {
			try {
				em.close();
			} catch (Exception e) {
				System.out.println("Erreur close :" + e.getMessage());
			}

		}
		return listCat;
	}

	@Override
	public void remove(Category var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		Category remove = null;
		
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
