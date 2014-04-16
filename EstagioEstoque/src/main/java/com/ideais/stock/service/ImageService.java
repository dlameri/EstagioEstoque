package com.ideais.stock.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.ImageDao;
import com.ideais.stock.domain.Image;

public class ImageService {
	
	static final Logger LOG = Logger.getLogger(ImageService.class);

	@Autowired
	ImageDao imageDao;

	public Image save(Image image, Boolean main) {
		image.setMain(main);
		try {
			return imageDao.save(image);
		} catch (HibernateException e) {
			LOG.error("Error ao salvar a imagem ", e);
			return null;
		}
	}

	public Image findById(Long id) {
		try {
			return imageDao.findById(id);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar a imagem ("+id+")", e);
			return null;
		}
	}

	public List<Image> findAll() {
		try {
			return imageDao.findAll();
		} catch (HibernateException e) {
			LOG.error("Error ao pegar todas as imagens ", e);
			return null;
		}
	}

	public void delete(Image image) {
		try {
			imageDao.delete(image);
		} catch (HibernateException e) {
			LOG.error("Error ao deletar a imagem ", e);
		}
	}
	
}
