package com.ideais.stock.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ideais.stock.domain.Category;


public class CategoryDao {

	private SessionFactory sessionFactory;

	public CategoryDao() {
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings( configure.getProperties() ).buildServiceRegistry();
		sessionFactory = configure.buildSessionFactory(serviceRegistry);
		
	}
	
	public Long create(Category category) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			Long id = (Long) session().save(category);
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
	public List<Category> findAll() {
		Transaction tx = session().beginTransaction();
		List<Category> category = session().createCriteria(Category.class).setCacheable(true).addOrder(Property.forName("name").asc()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		session().close();
		return category;
	}

	
	public void update(Category category) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().update(category);
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
	
	public Category findById(Long id) {
		Transaction tx = session().beginTransaction();
		Category category = (Category) session().get(Category.class, id);
		tx.commit();
		session().close();
		return category;
	}
	
	public Category findByName(String name) {
		Transaction tx = session().beginTransaction();
		Category category = (Category) session().createCriteria(Category.class).
				add(Restrictions.eq("name", name)).uniqueResult();
		tx.commit();
		session().close();
		return category;
	}
	
	public void delete(Category category) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().delete( session().merge(category) );
			tx.commit();
		} catch (HibernateException e) {
			if( tx != null ) {
				tx.rollback();
			}
		}
		session().close();
	}
	
	private Session session() {
		Session session = sessionFactory.getCurrentSession();
		if( session == null ) {
			throw new SessionException("Sessão está nula");
		}
		return session;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}