package com.ideais.stock.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> {

	private static final int DEFAULT_OFFSET_VALUE = 0;
	private static final int DEFAULT_MAX_RESULTS_VALUE = 20;
	private static final int MAXIMUM_MAX_RESULTS_VALUE = 50;

	@Autowired
	private SessionFactory sessionFactory;

	protected Session session() {
		return sessionFactory.getCurrentSession();
	}

	protected T save(T entity) {
		session().saveOrUpdate(entity);
		return entity;
	}

	protected void delete(T entity) {
		session().delete(session().merge(entity));
	}

	@SuppressWarnings("unchecked")
	protected T findById(Class<? extends Object> persistenceClass, Long id) {
		return (T) session().get(persistenceClass, id);
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByRestrictions(
			Class<? extends Object> persistenceClass,
			List<Criterion> restrictions) {
		Criteria criteria = session().createCriteria(persistenceClass);
		for (Criterion restriction : restrictions) {
			criteria.add(restriction);
		}
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findAll(Class<T> persistenceClass) {
		return session().createCriteria(persistenceClass)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findAll(Class<T> persistenceClass, Order orderBy) {
		return session().createCriteria(persistenceClass).addOrder(orderBy)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findAll(Class<T> persistenceClass, Order orderBy,
			List<Criterion> restrictions) {
		Criteria criteria = session().createCriteria(persistenceClass);
		for (Criterion restriction : restrictions) {
			criteria.add(restriction);
		}
		return criteria.addOrder(orderBy)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByParams(Class<T> persistenceClass,
			List<Criterion> restrictions, String orderColum, String order,
			String active, String firstResult, String maxResults)
			throws HibernateException {

		Criteria criteria = session().createCriteria(persistenceClass);

		for (Criterion restriction : restrictions) {
			criteria.add(restriction);
		}

		if (orderColum != null) {
			if ("asc".equals(order)) {
				criteria.addOrder(Property.forName(orderColum).asc());
			}
			if ("desc".equals(order)) {
				criteria.addOrder(Property.forName(orderColum).desc());
			}
		}

		criteria.add(Restrictions.like("active", Boolean.valueOf(active)));

		criteria.setFirstResult(setFirstResult(firstResult));
		criteria.setMaxResults(setMaxResult(maxResults));

		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	public int getCount(Class<T> persistenceClass,
			List<Criterion> restrictions, Boolean active)
			throws HibernateException {

		Criteria criteria = session().createCriteria(persistenceClass);

		for (Criterion restriction : restrictions) {
			criteria.add(restriction);
		}

		criteria.add(Restrictions.like("active", active));

		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list().size();
	}

	private int setFirstResult(String firstResult) {
		Integer offsetNumber;
		try {
			offsetNumber = Integer.parseInt(firstResult);
			if (offsetNumber < 0) {
				return DEFAULT_OFFSET_VALUE;
			}
			return offsetNumber;
		} catch (NumberFormatException e) {
			return DEFAULT_OFFSET_VALUE;
		}
	}

	private int setMaxResult(String maxResult) {
		Integer intToTest;

		try {
			intToTest = Integer.parseInt(maxResult);
			if (intToTest >= MAXIMUM_MAX_RESULTS_VALUE) {
				return MAXIMUM_MAX_RESULTS_VALUE;
			}
			if (intToTest < 1) {
				return DEFAULT_MAX_RESULTS_VALUE;
			}
			return intToTest;
		} catch (NumberFormatException e) {
			return DEFAULT_MAX_RESULTS_VALUE;
		}
	}
}
