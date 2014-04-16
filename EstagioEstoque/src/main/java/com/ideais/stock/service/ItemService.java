package com.ideais.stock.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.ItemDao;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;

public class ItemService {
	
	static final Logger LOG = Logger.getLogger(ImageService.class);

	@Autowired
	ItemDao itemDao;

	public Item save(Item item) {
		item.setRank(0);
		item.setActive(true);
		try {
			return itemDao.save(item);
		} catch (HibernateException e) {
			LOG.error("Error ao salvar o item ", e);
			return null;
		}
	}

	public Item findById(Long id) {
		try {
			return itemDao.findById(id);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar o item ("+id+")", e);
			return null;
		}
	}
	
	public List<Item> findByIds(List<Long> ids) {
		try {
			return findByIds(ids);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar os itens pelos ids ("+ids+")", e);
			return null;
		}
	}
	
	public List<Item> findByProductId(Product product) {
		try {
			return findByProductId(product);
		} catch (HibernateException e) {
			LOG.error("Error ao pega o item pelo produto ", e);
			return null;
		}
	}
	
	public List<Item> findByProductId(Product product, String orderColumn, String order, String active, String firstResult, String maxResults) {
		try {
			return findByProductId(product, orderColumn, order, active, firstResult, maxResults);
		} catch (HibernateException e) {
			LOG.error("Error ao pega o item pelo produto. Parametros passados: orderColumn: " + orderColumn + "; order: " + order + "; active: " + active + "; firstResult: " + firstResult + "; maxResults: " +  maxResults, e);
			return null;
		}
	}
	
	public List<Item> personalizedQuery(String orderColumn, String order, String active, String firstResult, String maxResults) {
		try {
			return itemDao.personalizedQuery(orderColumn, order, active, firstResult, maxResults);
		} catch (HibernateException e) {
			LOG.error("Error ao pega o item. Parametros passados: orderColumn: " + orderColumn + "; order: " + order + "; active: " + active + "; firstResult: " + firstResult + "; maxResults: " +  maxResults, e);
			return null;
		}
	}

	public List<Item> findAll() {
		try {
			return itemDao.findAll();
		} catch (HibernateException e) {
			LOG.error("Error ao pegar todos os itens ", e);
			return null;
		}
	}

	public void delete(Item item) {
		try {
			item.softDelete();
			itemDao.save(item);
		} catch (HibernateException e) {
			LOG.error("Error ao deletar o item ", e);
		}
	}
	
	public void delete(Product product) {
		try {
			List<Item> items = itemDao.findByProductId(product);
			
			if (items != null) {
				for (Item item : items) {
					item.softDelete();	
				}
			}
		} catch (HibernateException e) {
			LOG.error("Error ao deletar os itens ", e);
		}
	}
	
}
