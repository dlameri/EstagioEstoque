package com.ideais.stock.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ideais.stock.domain.Image;

public class ImageDao {
	
	private SessionFactory sessionFactory;

	public ImageDao() {
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings( configure.getProperties() ).buildServiceRegistry();
		sessionFactory = configure.buildSessionFactory(serviceRegistry);
		
	}
	
	public Long create(Image image) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			Long id = (Long) session().save(image);
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
	public List<Image> findAll() {
		Transaction tx = session().beginTransaction();
		List<Image> image = session().createCriteria(Image.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		session().close();
		return image;
	}

	
	public void update(Image image) {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().update(image);
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
	
	public Image findById(Long id) {
		Transaction tx = session().beginTransaction();
		Image image = (Image) session().get(Image.class, id);
		tx.commit();
		session().close();
		return image;
	}
	
	private Session session() {
		Session session = sessionFactory.getCurrentSession();
		if( session == null ) {
			throw new SessionException("Sessão está nula");
		}
		return session;
	}

}
