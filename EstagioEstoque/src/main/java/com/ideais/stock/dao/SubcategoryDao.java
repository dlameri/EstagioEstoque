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
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;

public class SubcategoryDao extends AbstractDao<Subcategory> {
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Subcategory save(Subcategory subcategory) {
		return super.save(subcategory);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Subcategory subcategory) {
		super.delete(subcategory);
	}
	
	public Subcategory findById(Long id) {
		return super.findById(Subcategory.class, id);
	}
	
	public List<Subcategory> findAll(Boolean active) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("active", active));
		
		return super.findAll(Subcategory.class, Order.asc("name"), restrictions);
	}

	public List<Subcategory> findByCategoryId(Category category, Boolean active) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("category", category) );
		restrictions.add( Restrictions.eq("active", active));
		
		return super.findByRestrictions( Subcategory.class, restrictions );
	}

}