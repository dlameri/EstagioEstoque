package com.ideais.stock.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.ItemDao;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Pagination;
import com.ideais.stock.domain.Product;

public class ItemService {

	static final Logger LOG = Logger.getLogger(ImageService.class);

	@Autowired
	ItemDao itemDao;

	public Item save(Item item) {
		if (item.getId() == null) {
			return create(item);
		}
		return update(item);
	}

	private Item create(Item item) {
		item.setRank(0);
		if(item.getActive() == null)
			item.setActive(false);
		try {
			return itemDao.save(item);
		} catch (HibernateException e) {
			LOG.error("Error ao salvar o item ", e);
			return null;
		}
	}

	private Item update(Item item) {
		Item itemToBeEdited = itemDao.findById(item.getId());
		
		itemToBeEdited.setSku(item.getSku());
		itemToBeEdited.setPriceFor(item.getPriceFor());
		itemToBeEdited.setPriceFrom(item.getPriceFrom());
		itemToBeEdited.setOptionName(item.getOptionName());
		itemToBeEdited.setOptionValue(item.getOptionValue());
		itemToBeEdited.setStock(item.getStock());
		itemToBeEdited.setActive(item.getActive());
		
		if (item.getActive() == null) 
			itemToBeEdited.setActive(false);
		
		try {
			return itemDao.save(itemToBeEdited);
		} catch (HibernateException e) {
			LOG.error("Error ao salvar o item ", e);
			return null;
		}
	}

	public Item findById(Long id) {
		try {
			return itemDao.findById(id);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar o item (" + id + ")", e);
			return null;
		}
	}

	public List<Item> findByIds(List<Long> ids) {
		try {
			return itemDao.findByIds(ids);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar os itens pelos ids (" + ids + ")", e);
			return null;
		}
	}

	public List<Item> findByProductId(Product product, Boolean active) {
		try {
			return itemDao.findByProductId(product, active);
		} catch (HibernateException e) {
			LOG.error("Error ao pega o item pelo produto ", e);
			return null;
		}
	}

	public List<Item> findByProductId(Product product, Boolean active, Pagination pagination) {
		try {
			return itemDao.findByProductId(product, active, pagination);
		} catch (HibernateException e) {
			LOG.error(
					"Error ao pega o item pelo produto. Parametros passados: orderColumn: "
							+ pagination.getOrderColumn() + "; order: " + pagination.getOrder() + "; active: "
							+ active + "; firstResult: " + pagination.getFirstResult()
							+ "; maxResults: " + pagination.getMaxResults(), e);
			return null;
		}
	}

	public List<Item> findAllWithPagination(Boolean active, Pagination pagination) {
		try {
			return itemDao.findAllWithPagination(active, pagination);
		} catch (HibernateException e) {
			LOG.error(
					"Error ao pega o item. Parametros passados: orderColumn: "
							+ pagination.getOrderColumn() + "; order: " + pagination.getOrder() + "; active: "
							+ active + "; firstResult: " + pagination.getFirstResult()
							+ "; maxResults: " + pagination.getMaxResults(), e);
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
	
	public int getCount(Boolean active, Product product) {
		return itemDao.getCount(active, product);
	}

	public Item delete(Item item) {
		try {
			item.softDelete();
			return itemDao.save(item);
		} catch (HibernateException e) {
			LOG.error("Error ao deletar o item ", e);
			return null;
		}
	}

	public void delete(Product product) {

		List<Item> items = itemDao.findByProductId(product, true);

		if (items != null) {
			for (Item item : items) {
				item.softDelete();
			}
		}
	}

}
