package com.ideais.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.ProductDao;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;

public class ProductService implements BaseService<Product> {

	@Autowired
	ProductDao productDao = new ProductDao();

	public Product save(Product product) {
		return productDao.save(product);
	}

	public Product findById(Long id) {
		return productDao.findById(id);
	}

	public List<Product> findAll() {
		return productDao.findAll();
	}

	public void delete(Product product) {
		List<Item> items = product.getItems();

		if (items != null) {
			for (Item item : items) {
				item.softDelete();
			}
		}

		product.softDelete();
		productDao.save(product);
	}

}
