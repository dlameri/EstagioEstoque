package com.ideais.stock.dao;

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
}