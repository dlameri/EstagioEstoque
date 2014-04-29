package com.ideais.stock.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Category;

public class CategoryDao extends AbstractDao<Category>{
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Category save(Category category) {
		return super.save(category);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Category category) {
		super.delete(category);
	}
	
	public Category findById(Long id) {
		return super.findById(Category.class, id);
	}
	
	public List<Category> findAll() {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("active", true));
		
		return super.findAll(Category.class, Order.asc("name"), restrictions);
	}
	
	public List<Category> findByName(String name) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.eq("name", name) );
		
		return findByRestrictions( Category.class, restrictions );
	}
	
	public List<Category> personalizedQuery(String orderColumn, String order, Boolean active, int firstResult, int maxResults) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		
		List<Category> categories = findByParams(Category.class, restrictions, orderColumn, order, String.valueOf(active), String.valueOf(firstResult), String.valueOf(maxResults));

		Integer count = ((BigInteger) session()
				.createSQLQuery("SELECT COUNT(CD_CATEGORIA) FROM CATEGORIA WHERE BO_ATIVO=" + active).list().get(0)).intValue();
		
		for (Category category: categories) {
			category.setCount(count);
		}
		
		return categories;
	}
	
	public List<Category> search (String orderColumn, String order, Boolean active, int firstResult, int maxResults, String textToSearch) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("name", "%"+textToSearch+"%") );

		List<Category> categories = findByParams(Category.class, restrictions, orderColumn, order, String.valueOf(active), String.valueOf(firstResult), String.valueOf(maxResults));

		Integer count = ((BigInteger) session().createSQLQuery("SELECT COUNT(CD_CATEGORIA) FROM CATEGORIA WHERE BO_ATIVO =" + active  + " AND NM_NOME like \"%"+textToSearch+"%\"").list().get(0)).intValue();
		
		for (Category category: categories) {
			category.setCount(count);
		}

		return categories;
	}
	
	public int getCount(String orderColumn, String order, Boolean active, int firstResult, int maxResults) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		return super.getCount(Category.class, restrictions, orderColumn, order, String.valueOf(active), String.valueOf(firstResult), String.valueOf(maxResults));
	}
	
	public int getCount(String orderColumn, String order, Boolean active, int firstResult, int maxResults, String textToSearch) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("name", "%"+textToSearch+"%") );

		return super.getCount(Category.class, restrictions, orderColumn, order, String.valueOf(active), String.valueOf(firstResult), String.valueOf(maxResults));
	}
}