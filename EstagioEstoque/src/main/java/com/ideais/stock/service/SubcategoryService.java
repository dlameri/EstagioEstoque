package com.ideais.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.SubcategoryDao;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;

public class SubcategoryService implements BaseService<Subcategory> {

	@Autowired
	SubcategoryDao subcategoryDao = new SubcategoryDao();

	public Subcategory save(Subcategory subcategory) {
		return subcategoryDao.save(subcategory);
	}

	public Subcategory findById(Long id) {
		return subcategoryDao.findById(id);
	}

	public List<Subcategory> findAll() {
		return subcategoryDao.findAll();
	}

	public void delete(Subcategory subcategory) {
		List<Product> products = subcategory.getProducts();
		List<Item> items;

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

		subcategory.softDelete();
		subcategoryDao.save(subcategory);
	}

}
