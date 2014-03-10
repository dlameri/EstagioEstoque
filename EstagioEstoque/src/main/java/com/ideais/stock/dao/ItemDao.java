package com.ideais.stock.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;


public class ItemDao {
	private SessionFactory sessionFactory;

	public ItemDao() {
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings( configure.getProperties() )
																		.buildServiceRegistry();
		sessionFactory = configure.buildSessionFactory(serviceRegistry);
		
	}
	
	public Long create(Item item) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			Long id = (Long) session().save(item);
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
	public List<Item> findAll() {
		Transaction tx = session().beginTransaction();
		List<Item> item = session().createCriteria(Item.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		return item;
	}

	
	public void update(Item item) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().update(item);
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
	
	public Item findById(Long id) {
		Transaction tx = session().beginTransaction();
		Item item = (Item) session().get(Item.class, id);
		tx.commit();
		return item;
	}
	
	public void delete(Item item) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().delete( session().merge(item) );
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

	@SuppressWarnings("unchecked")
	public List<Item> findByProductId(Product product) {
		Transaction tx = session().beginTransaction();		
		List<Item> itens = session().createCriteria(Item.class).add(Restrictions.like("product", product)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		return itens;
	}
}