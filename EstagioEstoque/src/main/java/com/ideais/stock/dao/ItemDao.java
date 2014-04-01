package com.ideais.stock.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.factory.QueryFactory;


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
		item.setRank(0);
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
		session().close();
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> personalizedQuery(String orderColumn, String order, String active, String firstResult, String maxResults) {
		Transaction tx = session().beginTransaction();
		Criteria criteria = session().createCriteria(Item.class);
		
		try {
			criteria = QueryFactory.factory(criteria, orderColumn, order, active, firstResult, maxResults);
		} catch (SQLException e) {
			// TODO fazer o catch
		}
		
		List<Item> items = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		session().close();
		return items;
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> findByProductId(Product product, String orderColumn, String order, String active, String firstResult, String maxResults) {
		Transaction tx = session().beginTransaction();		
		Criteria criteria = session().createCriteria(Item.class).add(Restrictions.like("product", product));
		
		try {
			criteria = QueryFactory.factory(criteria, orderColumn, order, active, firstResult, maxResults);
		} catch (SQLException e) {
			System.out.println("----------------------- error ----------------------------------");
			// TODO fazer o catch
		}
		
		List<Item> itens = criteria.list();
		tx.commit();
		session().close();
		return itens;
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> findAllOrderByRank() {
		Transaction tx = session().beginTransaction();
		
		Criteria criteria = session().createCriteria(Item.class);
		criteria.addOrder(Property.forName("rank").desc());
		List<Item> item = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		tx.commit();
		session().close();
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
		session().close();
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> findByIds(List<Long> ids) {
		Transaction tx = session().beginTransaction();	
		List<Item> item = session().createCriteria(Item.class).add(Restrictions.in("id", ids)).list();
		tx.commit();
		session().close();
		return item;
	}
		
	private Session session() {
		Session session = sessionFactory.getCurrentSession();
		if( session == null ) {
			throw new SessionException("Sessão está nula");
		}
		return session;
	}
}