package com.ideais.stock.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.AdminDao;
import com.ideais.stock.domain.Admin;

public class AdminService {
	
	static final Logger LOG = Logger.getLogger(AdminService.class);

	@Autowired
	AdminDao adminDao;

	public Admin save(Admin admin) {
		try {
			return adminDao.save(admin);
		} catch (HibernateException e) {
			LOG.error("Error ao salvar o administrador ", e);
			return null;
		}
	}

	public Admin findById(Long id) {
		try {
			return adminDao.findById(id);
		} catch (HibernateException e) {
			LOG.error("Error ao pegar o administrador ("+id+") ", e);
			return null;
		}
	}

	public List<Admin> findAll() {
		try {
			return adminDao.findAll();
		} catch (HibernateException e) {
			LOG.error("Error ao pegar todos os administradores ", e);
			return null;
		}
	}

	public void delete(Admin admin) {
		try {
			adminDao.delete(admin);
		} catch (HibernateException e) {
			LOG.error("Error ao deletar o administrador ", e);
		}
	}

	public Boolean authorize(Admin admin) {
		try {
			return adminDao.authorize(admin);
		} catch (HibernateException e) {
			LOG.error("Error ao checar a autorização do administrador ", e);
			return false;
		}
	}

}