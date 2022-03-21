package com.mycommerce.project.dao;

import java.util.ArrayList;
import java.util.List;

import com.mycommerce.project.dao.base.UserDao;
import com.mycommerce.project.model.Product;
import com.mycommerce.project.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JpaUserDao implements UserDao{

	@Override
	public void add(User user) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			
			em.persist(user);
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void update(User user) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			
			em.merge(user);
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}		
	}

	@Override
	public User findById(Long id) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		User user = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			Query query = em.createQuery("from User where id = :id");
			query.setParameter("id", id);

			user = (User) query.getSingleResult();
						
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}

		return user;
	}

	@Override
	public List<User> getAll() {
		List<User>  listUser = new ArrayList<>();
		EntityManager em = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();

			Query query = em.createQuery("from User");
			
			listUser = (List<User>) query.getResultList();
			
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
		}finally {
			try {
				em.close();
			} catch (Exception e) {
				System.out.println("Erreur close :" + e.getMessage());
			}

		}
		return listUser;
	}

	@Override
	public void remove(User var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		User remove = null;
		
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
	public User findByName(String name) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		User user = null;
		
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			Query query = em.createQuery("from User where name = :name");
			query.setParameter("name", name);

			user = (User) query.getSingleResult();
						
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}

		return user;
	}
	
	public void addInBasket(Product prod) {
		EntityManager em = null;
		EntityTransaction transaction = null;
				
		try {
			em = JPADaoManager.getInstence().getEmf().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			List<User> prods = prod.getInBasket();
			for (User user : prods) {
				List<Product> panier = user.getBasket();
				for (Product p : panier) {
					System.out.println("aled: "+user.getName() + " - " +p.getName());
					em.persist(p);
				}

				
			}
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Erreur :" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

}
