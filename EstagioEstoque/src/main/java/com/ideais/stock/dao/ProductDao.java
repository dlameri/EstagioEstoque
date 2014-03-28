package com.ideais.stock.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.factory.QueryFactory;


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
		product.setActive(true);
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
		Query q = (Query) session().createQuery("from Product").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Product> product = q.list();
		tx.commit();
		return product;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> personalizedQuery(String orderColum, String order, String active, String firstResult, String maxResults) {
		Transaction tx = session().beginTransaction();
		Criteria c = session().createCriteria(Product.class);
		
		try {
			c = QueryFactory.factory(c, orderColum, order, active, firstResult, maxResults);
			System.out.println("------------------------ >>>  orderColum: " + orderColum +", order: "+ order + ", " + active + ", " + firstResult + ", " + maxResults);
		} catch (SQLException e) {
			System.out.println("----------------  SQL ERROR ---------------- ");
			System.out.println("criterea: " + c + ", orderColum: "+ orderColum +", order: "+ order + ", " + active + ", " + firstResult + ", " + maxResults);
			e.printStackTrace();
		}
		
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Product> product = c.list();
		tx.commit();
		return product;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findAllOrderByRank() {
		Transaction tx = session().beginTransaction();
		List<Product> product = session().createCriteria(Product.class).addOrder(Property.forName("rank").desc()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

	@SuppressWarnings("unchecked")
	public List<Product> seach(String textToSeach) {
		Transaction tx = session().beginTransaction();		
		List<Product> products = session().createCriteria(Product.class).add(Restrictions.like("name", "%"+textToSeach+"%")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		return products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findByCategoryId(Category category) {
		Transaction tx = session().beginTransaction();		
		List<Product> products = session().createCriteria(Product.class).add(Restrictions.like("category", category)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		return products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findBySubcategoryId(Subcategory subcategory) {
		Transaction tx = session().beginTransaction();		
		List<Product> products = session().createCriteria(Product.class).add(Restrictions.like("subcategory", subcategory)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		return products;
	}
	
	private Session session() {
		Session session = sessionFactory.getCurrentSession();
		if( session == null ) {
			throw new SessionException("Sessão está nula");
		}
		return session;
	}
}