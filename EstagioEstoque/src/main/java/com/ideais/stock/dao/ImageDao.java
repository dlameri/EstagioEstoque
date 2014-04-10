package com.ideais.stock.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Image;

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
}
