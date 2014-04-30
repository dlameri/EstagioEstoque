package com.ideais.stock.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.ProductDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Pagination;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;

public class ProductService {

	static final Logger LOG = Logger.getLogger(ProductService.class);

	@Autowired
	ProductDao productDao;
	@Autowired
	ItemService itemService;

	public Product save(Product product) {
		product.setRank(0);
		product.setActive(true);
		try {
			return productDao.save(product);
		} catch (HibernateException e) {
			LOG.error("Error ao salvar o produto ", e);
			return null;
		}
	}

	public Product findById(Long id) {
		try {
			LOG.debug("Retornando o produto de id: "+ id);
			return productDao.findById(id);
		} catch (HibernateException e) {
			LOG.error("Error ao salvar o produto ", e);
			return null;
		}
	}

	public List<Product> findAll(Boolean active) {
		try {
			return productDao.findAll(active);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar todos os produtos ", e);
			return null;
		}
	}

	public List<Product> findByCategoryId(Category category, Boolean active, Pagination pagination) {
		try {
			return productDao.findByCategoryId(category, active, pagination);
		} catch (HibernateException e) {
			LOG.error("Erro ao pegar produto pela categoria.\nParametros passados: orderColumn: "
							+ pagination.getOrderColumn() + "; order: " + pagination.getOrder() + "; active: "
							+ active + "; firstResult: " + pagination.getFirstResult()
							+ "; maxResults: " + pagination.getMaxResults(), e);
			return null;
		}
	}

	public List<Product> findBySubcategoryId(Subcategory subcategory, Boolean active) {
		try {
			return productDao.findBySubcategoryId(subcategory, active);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar o produto pela subcategoria ", e);
			return null;
		}
	}

	public List<Product> findAllWithPagination(Boolean active, Pagination pagination, Boolean hasItems) {
		try {
			return productDao.findAllWithPagination(active, pagination, hasItems);
		} catch (HibernateException e) {
			LOG.error(
					"Error ao pegar o produto. Parametros passados: orderColumn: "
							+ pagination.getOrderColumn() + "; order: " + pagination.getOrder() + "; active: "
							+ active + "; firstResult: " + pagination.getFirstResult()
							+ "; maxResults: " + pagination.getMaxResults() + "; hasItems: " + hasItems, e);
			return null;
		}
	}

	public List<Product> search(Boolean active, Pagination pagination, String textToSearch) {
		try {
			return productDao.search(active, pagination, textToSearch);
		} catch (HibernateException e) {
			LOG.error("Error ao fazer a busca. Parametro passado: "
					+ textToSearch, e);
			return null;
		}
	}

	public Product delete(Product product) {
		itemService.delete(product);
		
		try {
			product.softDelete();
			return productDao.save(product);
		} catch (HibernateException e) {
			LOG.error("Error ao deletar o produto ", e);
			return null;
		}
	}

	public void delete(Subcategory subcategory) {
		List<Product> products = productDao.findBySubcategoryId(subcategory, true);

		if (products != null) {
			for (Product product : products) {
				itemService.delete(product);
				product.softDelete();
			}
		}
	}

}
