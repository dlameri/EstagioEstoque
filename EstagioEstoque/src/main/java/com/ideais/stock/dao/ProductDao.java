package com.ideais.stock.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Pagination;
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
	
	public List<Product> findByCategoryId(Category category, Boolean active, Pagination pagination) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		restrictions.add(Restrictions.isNotEmpty("items"));
		restrictions.add(Restrictions.like("category", category));

		List<Product> products = findByParams(Product.class, restrictions, active, pagination);

		Integer count = ((BigInteger) session().createSQLQuery("SELECT COUNT(CD_PRODUTO) FROM PRODUTO WHERE BO_ATIVO =" + active + " AND CD_CATEGORIA =" + category.getId()).list().get(0)).intValue();
		
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
	
	public List<Product> findAllWithPagination(Boolean active, Pagination pagination, Boolean hasItems) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		
		if (hasItems)
			restrictions.add(Restrictions.isNotEmpty("items"));
		
		List<Product> products = findByParams(Product.class, restrictions, active, pagination);

		Integer count = ((BigInteger) session().createSQLQuery("SELECT COUNT(CD_PRODUTO) FROM PRODUTO WHERE BO_ATIVO =" + active).list().get(0)).intValue();
		
		for (Product product: products) {
			product.setCount(count);
		}
		
		return products;
	}

	public List<Product> search(Boolean active, Pagination pagination, String textToSearch) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		Disjunction disjunction = Restrictions.or();
		
		String getCountQuery = "SELECT COUNT(CD_PRODUTO) FROM PRODUTO WHERE BO_ATIVO=" + active;
		
		String[] splittedQuery = textToSearch.split(" ");
		getCountQuery = getCountQuery.concat(" AND (" ); 
		
		for (int i = 0; i < splittedQuery.length ; i++ ) {
			disjunction.add( Restrictions.like("name", "%"+splittedQuery[i]+"%") );
			disjunction.add( Restrictions.like("longDescription", "%"+splittedQuery[i]+"%") );

			if (i == 0)
				getCountQuery = getCountQuery.concat(" NM_NOME like \"%"+splittedQuery[i]+"%\"");
			else
				getCountQuery = getCountQuery.concat(" OR NM_NOME like \"%"+splittedQuery[i]+"%\"");
				getCountQuery = getCountQuery.concat(" OR NM_DESCRICAO_LONGA like \"%"+splittedQuery[i]+"%\"");
		}
		getCountQuery = getCountQuery.concat(")" );
		
		restrictions.add(disjunction);
		
		List<Product> products = findByParams(Product.class, restrictions, active, pagination);

		Integer count = ((BigInteger) session().createSQLQuery(getCountQuery).list().get(0)).intValue();
		
		for (Product product: products) {
			product.setCount(count);
		}
		
		return products;
	}
	
	public List<Product> findPromoProducts() {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		restrictions.add(Restrictions.like("promo", true));

		return super.findByRestrictions(Product.class, restrictions);
	}
	
	public int getCount(Boolean active) {
		return super.getCount(active, "PRODUTO");
	}
	
	public int getCount(Boolean active, String textToSearch) {
		return super.getCount(active, "PRODUTO", textToSearch);
	}
}