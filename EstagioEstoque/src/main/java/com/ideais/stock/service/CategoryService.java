package com.ideais.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;

public class CategoryService implements BaseService<Category> {

	@Autowired
	CategoryDao categoryDao = new CategoryDao();

	public Category save(Category category) {
		return categoryDao.save(category);
	}

	public Category findById(Long id) {
		return categoryDao.findById(id);
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Category category) {
		List<Subcategory> subcategories = category.getSubcategories();
		List<Product> products;
		List<Item> items;

		if (subcategories != null) {
			for (Subcategory subcategory : subcategories) {
				subcategory.softDelete();
				products = subcategory.getProducts();
				if (products != null) {
					for (Product product : products) {
						product.softDelete();
						items = product.getItems();
						if (items != null) {
							for (Item item : items) {
								item.softDelete();
							}
						}
					}
				}
			}
		}
		
		category.softDelete();
		categoryDao.save(category);
	}
	
}
