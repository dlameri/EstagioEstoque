package com.ideais.stock.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Product;


public class CategoryDao extends AbstractDao<Category>{

	@Transactional(propagation=Propagation.REQUIRED)
	public Category create(Category category) {
		return super.create(category);
	}
	
	public List<Category> findAll() {
		return super.findAll(Category.class, Order.asc("name"));
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
			
			ProductDao productDao = new ProductDao();
			
			List<Product> products = productDao.findByCategoryId(category);
			
			for (Product product : products) {
				product.softDelete();
			}
						
			session().delete( session().merge(category) );
			tx.commit();
		} catch (HibernateException e) {
			if( tx != null ) {
				tx.rollback();
			}
		}
		session().close();
	}
}