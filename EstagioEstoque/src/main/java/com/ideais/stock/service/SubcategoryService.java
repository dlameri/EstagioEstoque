package com.ideais.stock.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.dao.SubcategoryDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;

public class SubcategoryService {

	static final Logger LOG = Logger.getLogger(Subcategory.class);

	@Autowired
	SubcategoryDao subcategoryDao;
	@Autowired
	ProductService productService;

	public Subcategory save(Subcategory subcategory) {
		subcategory.setActive(true);
		try {
			return subcategoryDao.save(subcategory);
		} catch (HibernateException e) {
			LOG.error("Error ao salvar a subcategoria ", e);
			return null;
		}
	}

	public Subcategory findById(Long id) {
		try {
			return subcategoryDao.findById(id);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar a subcategoria (" + id + ")", e);
			return null;
		}
	}

	public List<Subcategory> findByCategoryId(Category category, Boolean active) {
		try {
			return subcategoryDao.findByCategoryId(category, active);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar a subcategoria pela categoria ", e);
			return null;
		}
	}

	public List<Subcategory> findAll(Boolean active) {
		try {
			return subcategoryDao.findAll(active);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar todas as subcategorias ", e);
			return null;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Subcategory delete(Subcategory subcategory) {
		productService.delete(subcategory);
		
		try {
			subcategory.softDelete();
			return subcategoryDao.save(subcategory);
		} catch (HibernateException e) {
			LOG.error("Error ao deletar a subcategoria ", e);
			return null;
		}
	}

	public void delete(Category category) {
		List<Subcategory> subcategories = category.getSubcategories();

		if (subcategories != null) {
			for (Subcategory subcategory : subcategories) {
				productService.delete(subcategory);
				subcategory.softDelete();
			}
		}
	}
}
