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
import com.ideais.stock.domain.Pagination;

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
	
	public List<Category> findAllWithPagination(Boolean active, Pagination pagination) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		
		List<Category> categories = findByParams(Category.class, restrictions, active, pagination);

		Integer count = ((BigInteger) session()
				.createSQLQuery("SELECT COUNT(CD_CATEGORIA) FROM CATEGORIA WHERE BO_ATIVO=" + active).list().get(0)).intValue();
		
		for (Category category: categories) {
			category.setCount(count);
		}
		
		return categories;
	}
	
	public List<Category> search (Boolean active, Pagination pagination, String textToSearch) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("name", "%"+textToSearch+"%") );

		List<Category> categories = findByParams(Category.class, restrictions, active, pagination);

		Integer count = ((BigInteger) session().createSQLQuery("SELECT COUNT(CD_CATEGORIA) FROM CATEGORIA WHERE BO_ATIVO =" + active  + " AND NM_NOME like \"%"+textToSearch+"%\"").list().get(0)).intValue();
		
		for (Category category: categories) {
			category.setCount(count);
		}

		return categories;
	}
	
	public int getCount(Boolean active) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		return super.getCount(Category.class, restrictions, active);
	}
	
	public int getCount(Boolean active, String textToSearch) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("name", "%"+textToSearch+"%") );

		return super.getCount(Category.class, restrictions, active);
	}
}