package com.ideais.stock.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;

public class ImageDao extends AbstractDao<Image>{
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Image save(Image image) {
		return super.save(image);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Image image) {
		super.delete(image);
	}
	
	public Image findById(Long id) {
		return super.findById(Image.class, id);
	}
	
	public List<Image> findAll() {
		return super.findAll(Image.class);
	}
	
	public List<Image> findByItemId(Item item) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.eq("item", item));
		
		return super.findByRestrictions(Image.class, restrictions);
	}
}
