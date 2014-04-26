package com.ideais.stock.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.ProductDao;
import com.ideais.stock.domain.Category;
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

	public List<Product> findByCategoryId(Category category,
			String orderColumn, String order, String active,
			String firstResult, String maxResults) {
		try {
			return productDao.findByCategoryId(category, orderColumn, order,
					active, firstResult, maxResults);
		} catch (HibernateException e) {
			LOG.error("Erro ao pegar produto pela categoria.\nParametros passados: orderColumn: " + orderColumn + "; order: " + order + "; active: " + active + "; firstResult: " + firstResult + "; maxResults: " +  maxResults, e);
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

	public List<Product> personalizedQuery(String orderColumn, String order,
			String active, String firstResult, String maxResults) {
		try {
			return productDao.personalizedQuery(orderColumn, order, active,
					firstResult, maxResults);
		} catch (HibernateException e) {
			LOG.error(
					"Error ao pegar o produto. Parametros passados: orderColumn: "
							+ orderColumn + "; order: " + order + "; active: "
							+ active + "; firstResult: " + firstResult
							+ "; maxResults: " + maxResults, e);
			return null;
		}
	}

	public List<Product> search(String textToSearch, Boolean active) {
		try {
			return productDao.search(textToSearch, active);
		} catch (HibernateException e) {
			LOG.error("Error ao fazer a busca. Parametro passado: "
					+ textToSearch, e);
			return null;
		}
	}

	public void delete(Product product) {
		itemService.delete(product);
		
		try {
			product.softDelete();
			productDao.save(product);
		} catch (HibernateException e) {
			LOG.error("Error ao deletar o produto ", e);
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
