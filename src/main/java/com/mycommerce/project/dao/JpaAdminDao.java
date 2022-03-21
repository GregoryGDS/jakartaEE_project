package com.mycommerce.project.dao;

import java.util.ArrayList;
import java.util.List;

import com.mycommerce.project.dao.base.AdminDao;
import com.mycommerce.project.model.Admin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JpaAdminDao implements AdminDao {

	@Override
	public void add(Admin admin) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			
			em.persist(admin);
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void update(Admin admin) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			
			em.merge(admin);
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public Admin findById(Long id) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		Admin admin = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			Query query = em.createQuery("from Admin where id = :id");
			query.setParameter("id", id);

			admin = (Admin) query.getSingleResult();
						
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}

		return admin;
	}

	@Override
	public List<Admin> getAll() {
		List<Admin>  listAdmin = new ArrayList<>();
		EntityManager em = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();

			Query query = em.createQuery("from Admin");
			
			listAdmin = (List<Admin>) query.getResultList();
			
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
		}finally {
			try {
				em.close();
			} catch (Exception e) {
				System.out.println("Erreur close :" + e.getMessage());
			}

		}
		return listAdmin;
	}

	@Override
	public void remove(Admin var1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Long id) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		Admin remove = null;
		
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

	@Override
	public Admin findByName(String name) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		Admin admin = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			Query query = em.createQuery("from Admin where name = :name");
			query.setParameter("name", name);

			admin = (Admin) query.getSingleResult();
						
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}

		return admin;
	}

}
