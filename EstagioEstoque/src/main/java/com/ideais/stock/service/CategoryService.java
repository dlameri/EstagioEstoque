package com.ideais.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.domain.Category;

public class CategoryService {

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	SubcategoryService subcategoryService;

	public Category save(Category category) {
		category.setActive(true);
		return categoryDao.save(category);
	}

	public Category findById(Long id) {
		return categoryDao.findById(id);
	}
	
	public List<Category> findByName(String name) {
		return categoryDao.findByName(name);
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Category category) {
		subcategoryService.delete(category);
		
		category.softDelete();
		categoryDao.save(category);
	}
	
}
