package com.ideais.stock.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Pagination;
import com.ideais.stock.domain.Product;


public class ItemDao extends AbstractDao<Item>{
	
	static final Logger LOG = Logger.getLogger(ItemDao.class);
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Item save(Item item) {
		return super.save(item);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Item item) {
		super.delete(item);
	}
	
	public Item findById(Long id) {
		return super.findById(Item.class, id);
	}
	
	public List<Item> findAll() {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("active", true));
		
		return super.findAll(Item.class, Order.asc("sku"), restrictions);
	}
	
	public List<Item> findAllOrderByRank() {
		return super.findAll(Item.class, Order.desc("rank"));
	}
	
	public List<Item> findByIds(List<Long> ids) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.in("id", ids) );
		
		return super.findByRestrictions(Item.class, restrictions);
	}
	
	public List<Item> findByProductId(Product product, Boolean active) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("product", product) );
		restrictions.add( Restrictions.eq("active", active) );
		
		return super.findByRestrictions(Item.class, restrictions);
	}

	public List<Item> findAllWithPagination(Boolean active, Pagination pagination) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		List<Item> items = findByParams(Item.class, restrictions, active, pagination);

		Integer count = ((BigInteger) session().createSQLQuery("SELECT COUNT(CD_ITEM) FROM ITEM WHERE BO_ATIVO =" + active).list().get(0)).intValue();
		
		for (Item item: items) {
			item.setCount(count);
		}
		
		return items;
	}
	
	public List<Item> findByProductId(Product product, Boolean active, Pagination pagination) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		restrictions.add(Restrictions.like("product", product));

		List<Item> items = findByParams(Item.class, restrictions, active, pagination);

		Integer count = ((BigInteger) session().createSQLQuery("SELECT COUNT(CD_ITEM) FROM ITEM WHERE BO_ATIVO =" + active + " AND CD_PRODUTO =" + product.getId()).list().get(0)).intValue();
		
		for (Item item: items) {
			item.setCount(count);
		}
		
		return items;
	}
}