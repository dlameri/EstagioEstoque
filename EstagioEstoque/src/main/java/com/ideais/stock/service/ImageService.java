package com.ideais.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.ImageDao;
import com.ideais.stock.domain.Image;

public class ImageService {

	@Autowired
	ImageDao imageDao;

	public Image save(Image image, Boolean main) {
		image.setMain(main);
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
