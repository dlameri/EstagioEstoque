package com.ideais.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.dao.SubcategoryDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;

public class SubcategoryService {

	@Autowired
	SubcategoryDao subcategoryDao;
	@Autowired
	ProductService productService;

	public Subcategory save(Subcategory subcategory) {
		subcategory.setActive(true);
		return subcategoryDao.save(subcategory);
	}

	public Subcategory findById(Long id) {
		return subcategoryDao.findById(id);
	}
	
	public List<Subcategory> findByCategoryId(Category category) {
		return subcategoryDao.findByCategoryId(category);
	}

	public List<Subcategory> findAll() {
		return subcategoryDao.findAll();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Subcategory subcategory) {
		productService.delete(subcategory);
		
		subcategory.softDelete();
		subcategoryDao.save(subcategory);
	}
	
	public void delete(Category category) {
		List<Subcategory> subcategories = subcategoryDao.findByCategoryId(category);
		
		if (subcategories != null) {
			for (Subcategory subcategory : subcategories) {
				productService.delete(subcategory);
				subcategory.softDelete();	
			}
		}
	}

}
