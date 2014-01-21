package com.ideais.stock.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ideais.stock.domain.Product;


public class ProductDao {
	private SessionFactory sessionFactory;

	public ProductDao() {
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings( configure.getProperties() )
																		.buildServiceRegistry();
		sessionFactory = configure.buildSessionFactory(serviceRegistry);
		
	}
	
	public Long create(Product product) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			Long id = (Long) session().save(product);
	        tx.commit();
	        return id;
		} catch (Exception e) {
			if( tx != null ) {
				tx.rollback();
			}
			e.printStackTrace();
			return null;
		} finally {
			session().close();
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		Transaction tx = session().beginTransaction();
		List<Product> product = session().createCriteria(Product.class).list();
		tx.commit();
		return product;
	}

	
	public void update(Product product) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().update(product);
	        tx.commit();
		} catch (Exception e) {
			if( tx != null ) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session().close();
		}
	}
	
	public Product findById(Long id) {
		Transaction tx = session().beginTransaction();
		Product product = (Product) session().get(Product.class, id);
		tx.commit();
		return product;
	}
	
	public void delete(Product product) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().delete( session().merge(product) );
			tx.commit();
		} catch (HibernateException e) {
			if( tx != null ) {
				tx.rollback();
			}
		}
	}
	
	private Session session() {
		Session session = sessionFactory.getCurrentSession();
		if( session == null ) {
			throw new SessionException("Sessão está nula");
		}
		return session;
	}
}