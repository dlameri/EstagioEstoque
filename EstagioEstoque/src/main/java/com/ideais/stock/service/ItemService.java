package com.ideais.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.ItemDao;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;

public class ItemService {

	@Autowired
	ItemDao itemDao;

	public Item save(Item item) {
		item.setRank(0);
		item.setActive(true);
		return itemDao.save(item);
	}

	public Item findById(Long id) {
		return itemDao.findById(id);
	}
	
	public List<Item> findAllOrderByRank() {
		return findAllOrderByRank();
	}
	
	public List<Item> findByIds(List<Long> ids) {
		return findByIds(ids);
	}
	
	public List<Item> findByProductId(Product product) {
		return findByProductId(product);
	}
	
	public List<Item> findByProductId(Product product, String orderColumn, String order, String active, String firstResult, String maxResults) {
		return findByProductId(product, orderColumn, order, active, firstResult, maxResults);
	}
	
	public List<Item> personalizedQuery(String orderColumn, String order, String active, String firstResult, String maxResults) {
		return itemDao.personalizedQuery(orderColumn, order, active, firstResult, maxResults);
	}

	public List<Item> findAll() {
		return itemDao.findAll();
	}

	public void delete(Item item) {
		item.softDelete();
		itemDao.save(item);
	}
	public void delete(Product product) {
		List<Item> items = itemDao.findByProductId(product);
		
		if (items != null) {
			for (Item item : items) {
				item.softDelete();	
			}
		}
	}
	
}
