package com.ideais.stock.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;


public class ProductDao extends AbstractDao<Product>{
	
	static final Logger LOG = Logger.getLogger(ProductDao.class);
	
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

	public List<Product> findAll(Boolean active) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("active", active));
		
		return super.findAll(Product.class, Order.asc("name"), restrictions);
	}
	
	public List<Product> findByCategoryId(Category category, String orderColumn, String order, String active, String firstResult, String maxResults) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		restrictions.add(Restrictions.isNotEmpty("items"));
		restrictions.add(Restrictions.like("category", category));

		List<Product> products = findByParams(Product.class, restrictions, orderColumn, order, active, firstResult, maxResults);

		Integer count = ((BigInteger) session().createSQLQuery("SELECT COUNT(CD_PRODUTO) FROM PRODUTO WHERE BO_ATIVO =" + parseStringToBoolean(active) + " AND CD_CATEGORIA =" + category.getId()).list().get(0)).intValue();
		
		for (Product product: products) {
			product.setCount(count);
		}
		
		return products;
	}
	
	public List<Product> findBySubcategoryId(Subcategory subcategory, Boolean active) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("subcategory", subcategory) );
		restrictions.add( Restrictions.eq("active", active) );
		
		return findByRestrictions(Product.class, restrictions);
	}
	
	public List<Product> personalizedQuery(String orderColumn, String order, String active, String firstResult, String maxResults, Boolean hasItems) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		if (hasItems)
			restrictions.add(Restrictions.isNotEmpty("items"));
		
		List<Product> products = findByParams(Product.class, restrictions, orderColumn, order, active, firstResult, maxResults);

		Integer count = ((BigInteger) session().createSQLQuery("SELECT COUNT(CD_PRODUTO) FROM PRODUTO WHERE BO_ATIVO =" + parseStringToBoolean(active)).list().get(0)).intValue();
		
		for (Product product: products) {
			product.setCount(count);
		}
		
		return products;
	}

	public List<Product> search(String orderColumn, String order, Boolean active, int firstResult, int maxResults, String textToSearch) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("name", "%"+textToSearch+"%") );

		List<Product> products = findByParams(Product.class, restrictions, orderColumn, order, String.valueOf(active), String.valueOf(firstResult), String.valueOf(maxResults));

		Integer count = ((BigInteger) session().createSQLQuery("SELECT COUNT(CD_PRODUTO) FROM PRODUTO WHERE BO_ATIVO =" + active  + " AND NM_NOME like \"%"+textToSearch+"%\"").list().get(0)).intValue();
		
		for (Product product: products) {
			product.setCount(count);
		}
		
		return products;
	}
}