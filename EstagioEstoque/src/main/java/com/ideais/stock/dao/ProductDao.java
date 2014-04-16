package com.ideais.stock.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.factory.QueryFactory;


public class ProductDao extends AbstractDao<Product>{
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Product save(Product product) {
		return super.save(product);
	}
	
	public void delete(Product product) {
		super.delete(product);
	}
	
	public Product findById(Long id) {
		return super.findById(Product.class, id);
	}

	public List<Product> findAll() {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("active", true));
		
		return super.findAll(Product.class, Order.asc("name"), restrictions);
	}
	
	public List<Product> findByCategoryId(Category category) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("category", category) );
		
		return findByRestrictions(Product.class, restrictions);
	}
	
	public List<Product> findBySubcategoryId(Subcategory subcategory) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("subcategory", subcategory) );
		
		return findByRestrictions(Product.class, restrictions);
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> personalizedQuery(String orderColum, String order, String active, String firstResult, String maxResults) {
		Criteria criteria = session().createCriteria(Product.class);
		List<Product> products = new ArrayList<Product>();
		
		try {
			criteria = QueryFactory.factory(criteria, orderColum, order, active, firstResult, maxResults);
			criteria.add(Restrictions.isNotEmpty("items"));
			products = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (HibernateException e) {
			LOG.error("Parametros passados: orderColumn: " + orderColum + "; order: " + order + "; active: " + active + "; firstResult: " + firstResult + "; maxResults: " +  maxResults, e);
		} 
		
		return products;
	}

	public List<Product> search(String textToSearch) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("name", "%"+textToSearch+"%") );

		return findByRestrictions(Product.class, restrictions);
	}
}