package com.ideais.stock.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.domain.Category;

public class CategoryService {
	
	static final Logger LOG = Logger.getLogger(CategoryService.class);

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	SubcategoryService subcategoryService;

	public Category save(Category category) {
		try {
			category.setActive(true);
			return categoryDao.save(category);
		} catch (HibernateException e) {
			LOG.error("Error ao salvar a categoria ", e);
			return null;
		}
	}

	public Category findById(Long id) {
		try {
			return categoryDao.findById(id);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar a categoria ("+id+")", e);
			return null;
		}
		
	}
	
	public List<Category> findByName(String name) {
		try {
			return categoryDao.findByName(name);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar a categoria pelo nome ("+name+")", e);
			return null;
		}
		
	}

	public List<Category> findAll() {
		try {
			return categoryDao.findAll();
		} catch (HibernateException e) {
			LOG.error("Error ao pegar todas as categorias ", e);
			return null;
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Category category) {
		try {
			subcategoryService.delete(category);
			
			category.softDelete();
			categoryDao.save(category);
		} catch (HibernateException e) {
			LOG.error("Error ao deletar a categoria ", e);
		}
		
	}
	
}
