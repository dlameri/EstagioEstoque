package com.ideais.stock.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Pagination;

public class CategoryService {
	
	static final Logger LOG = Logger.getLogger(CategoryService.class);

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	SubcategoryService subcategoryService;

	public Category save(Category category) {
		if (category.getId() == null) {
			return create(category);
		}
		return update(category);
	}
	
	private Category create(Category category) {
		try {
			category.setActive(true);
			return categoryDao.save(category);
		} catch (HibernateException e) {
			LOG.error("Error ao salvar a categoria ", e);
			return null;
		}
	}

	private Category update(Category category) {
		Category categoryToBeEdited = categoryDao.findById(category.getId());
		
		if (categoryToBeEdited == null) {
			return null;
		}
		
		categoryToBeEdited.setActive(category.getActive());
		categoryToBeEdited.setName(category.getName());
		
		try {
			return categoryDao.save(categoryToBeEdited);
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
	
	public List<Category> findAllWithPagination(Boolean active, Pagination pagination) {
		try {
			return categoryDao.findAllWithPagination(active, pagination);
		} catch (HibernateException e) {
			LOG.error(
					"Error ao pegar o produto. Parametros passados: orderColumn: "
							+ pagination.getOrderColumn() + "; order: " + pagination.getOrder() + "; active: "
							+ active + "; firstResult: " + pagination.getFirstResult()
							+ "; maxResults: " + pagination.getMaxResults(), e);
			return null;
		}
	}

	public List<Category> search(Boolean active, Pagination pagination, String textToSearch) {
		try {
			return categoryDao.search(active, pagination, textToSearch);
		} catch (HibernateException e) {
			LOG.error("Error ao fazer a busca. Parametro passado: "
					+ textToSearch, e);
			return null;
		}
	}
	
	public int getCount(Boolean active) {
		return categoryDao.getCount(active);
	}
	
	public int getCount(Boolean active, String textToSearch) {
		return categoryDao.getCount(active, textToSearch);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Category delete(Category category) {
		subcategoryService.delete(category);

		try {
			category.softDelete();
			return categoryDao.save(category);
		} catch (HibernateException e) {
			LOG.error("Error ao deletar a categoria ", e);
			return null;
		}
		
	}
	
}
