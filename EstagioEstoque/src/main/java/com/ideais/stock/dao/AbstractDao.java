package com.ideais.stock.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	protected T save(T entity) {
		session().saveOrUpdate(entity);
		return entity;
	}
	
	protected void delete (T entity) {
		session().delete( session().merge(entity) );
	}

	@SuppressWarnings("unchecked")
	protected T findById(Class<? extends Object> persistenceClass, Long id) {
		return (T) session().get(persistenceClass, id);
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findByRestrictions(Class<? extends Object> persistenceClass, List<Criterion> restrictions) {
		Criteria criteria = session().createCriteria(persistenceClass);
		for( Criterion restriction : restrictions ) {
			criteria.add( restriction );  
		}
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findAll( Class<? extends Object> persistenceClass ) {
		return session().createCriteria( persistenceClass ).list();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findAll(Class<T> persistenceClass, Order orderBy) {
		return session().createCriteria( persistenceClass ).addOrder(orderBy).list();
	}
}
