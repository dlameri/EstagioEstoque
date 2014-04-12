package com.ideais.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.ProductDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;

public class ProductService {

	@Autowired
	ProductDao productDao;
	@Autowired
	ItemService itemService;

	public Product save(Product product) {
		product.setRank(0);
		product.setActive(true);
		return productDao.save(product);
	}

	public Product findById(Long id) {
		return productDao.findById(id);
	}

	public List<Product> findAll() {
		return productDao.findAll();
	}
	
	public List<Product> findAllOrderByRank() {
		return productDao.findAllOrderByRank();
	}
	
	public List<Product> findByCategoryId(Category category) {
		return productDao.findByCategoryId(category);
	}
	
	public List<Product> findBySubcategoryId(Subcategory subcategory) {
		return productDao.findBySubcategoryId(subcategory);
	}
	
	public List<Product> personalizedQuery(String orderColum, String order, String active, String firstResult, String maxResults) {
		return productDao.personalizedQuery(orderColum, order, active, firstResult, maxResults);
	}
	
	public List<Product> search(String textToSearch) {
		return productDao.search(textToSearch);
	}

	public void delete(Product product) {
		itemService.delete(product);
		
		product.softDelete();
		productDao.save(product);
	}
	
	public void delete(Subcategory subcategory) {
		List<Product> products = productDao.findBySubcategoryId(subcategory);
		
		if (products != null) {
			for (Product product : products) {
				itemService.delete(product);
				product.softDelete();	
			}
		}
	}

}
