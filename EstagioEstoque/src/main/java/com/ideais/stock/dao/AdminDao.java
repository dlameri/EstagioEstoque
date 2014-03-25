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

import com.ideais.stock.domain.Admin;


public class AdminDao {

	private SessionFactory sessionFactory;

	public AdminDao() {
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings( configure.getProperties() ).buildServiceRegistry();
		sessionFactory = configure.buildSessionFactory(serviceRegistry);	
	}
	
	public Long create(Admin admin) {
//		admin.setPassword(Admin.makeSecurePassword(admin.getPassword()));
//		admin.makeSecurePassword();
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			Long id = (Long) session().save(admin);
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
	public List<Admin> findAll() {
		Transaction tx = session().beginTransaction();
		List<Admin> category = session().createCriteria(Admin.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		return category;
	}

	
	public void update(Admin admin) {
		Transaction tx = null;
//		admin.makeSecurePassword();
		try {
			tx = session().beginTransaction();
			session().update(admin);
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
	
	public Admin findById(Long id) {
		Transaction tx = session().beginTransaction();
		Admin admin = (Admin) session().get(Admin.class, id);
		tx.commit();
		return admin;
	}
	
	public void delete(Admin admin) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().delete( session().merge(admin) );
			tx.commit();
		} catch (HibernateException e) {
			if( tx != null ) {
				tx.rollback();
			}
		}
	}
	
	public Boolean authorized(String email, String password) {
		if (email == null || email.isEmpty()) {
			return false;
		}
		
		if (password == null || password.isEmpty()) {
			return false;
		}
		
		Transaction tx = session().beginTransaction();
		Admin admin = new Admin();
		admin.setPassword(password);
//		password = admin.makeSecurePassword(password);
		@SuppressWarnings("unchecked")
		List<Admin> admins = session().createCriteria(Admin.class).add(Restrictions.like("email", email)).add(Restrictions.like("password", admin.getPassword())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		
		if (admins.size() == 1){
			return true;
		}
		return false;
	}
	
	private Session session() {
		Session session = sessionFactory.getCurrentSession();
		if( session == null ) {
			throw new SessionException("Sessão está nula");
		}
		return session;
	}
}