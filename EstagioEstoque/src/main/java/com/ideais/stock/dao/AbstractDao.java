package com.ideais.stock.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	protected T create(T entity) {
		session().saveOrUpdate(entity);
		return entity;
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
