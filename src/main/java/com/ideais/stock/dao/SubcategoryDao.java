package main.java.com.ideais.stock.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import main.java.com.ideais.stock.domain.Category;
import main.java.com.ideais.stock.domain.Subcategory;

public class SubcategoryDao {
	private SessionFactory sessionFactory;

	public SubcategoryDao() {
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings( configure.getProperties() ).buildServiceRegistry();
		sessionFactory = configure.buildSessionFactory(serviceRegistry);
		
	}
	
	public Long create(Subcategory subcategory) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			Long id = (Long) session().save(subcategory);
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
	public List<Subcategory> findAll() {
		Transaction tx = session().beginTransaction();
		List<Subcategory> subcategory = session().createCriteria(Category.class).list();
		tx.commit();
		return subcategory;
	}

	
	public void update(Subcategory subcategory) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().update(subcategory);
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
	
	public Subcategory findById(Long id) {
		Transaction tx = session().beginTransaction();
		Subcategory subcategory = (Subcategory) session().get(Subcategory.class, id);
		tx.commit();
		return subcategory;
	}
	
	public void delete(Subcategory subcategory) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().delete( session().merge(subcategory) );
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