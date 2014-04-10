package com.ideais.stock.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.factory.QueryFactory;


public class ItemDao extends AbstractDao<Item>{
	
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
		return super.findAll(Item.class, Order.asc("name"));
	}
	
	public List<Item> findAllOrderByRank() {
		return super.findAll(Item.class, Order.desc("rank"));
	}
	
	public List<Item> findByIds(List<Long> ids) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.in("id", ids) );
		
		return super.findByRestrictions(Item.class, restrictions);
	}

	@SuppressWarnings("unchecked")
	public List<Item> personalizedQuery(String orderColumn, String order, String active, String firstResult, String maxResults) {
		Transaction tx = session().beginTransaction();
		Criteria criteria = session().createCriteria(Item.class);
		
		try {
			criteria = QueryFactory.factory(criteria, orderColumn, order, active, firstResult, maxResults);
		} catch (SQLException e) {
			// TODO fazer o catch
		}
		
		List<Item> items = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		session().close();
		return items;
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> findByProductId(Product product, String orderColumn, String order, String active, String firstResult, String maxResults) {
		Transaction tx = session().beginTransaction();		
		Criteria criteria = session().createCriteria(Item.class).add(Restrictions.like("product", product));
		
		try {
			criteria = QueryFactory.factory(criteria, orderColumn, order, active, firstResult, maxResults);
		} catch (SQLException e) {
			// TODO fazer o catch
		}
		
		List<Item> itens = criteria.list();
		tx.commit();
		session().close();
		return itens;
	}
}