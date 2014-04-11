package com.ideais.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.ImageDao;
import com.ideais.stock.domain.Image;

public class ImageService implements BaseService<Image> {

	@Autowired
	ImageDao imageDao = new ImageDao();

	public Image save(Image image) {
		return imageDao.save(image);
	}

	public Image findById(Long id) {
		return imageDao.findById(id);
	}

	public List<Image> findAll() {
		return imageDao.findAll();
	}

	public void delete(Image image) {
		imageDao.delete(image);
	}
	
}
