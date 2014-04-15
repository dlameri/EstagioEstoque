package com.ideais.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.AdminDao;
import com.ideais.stock.domain.Admin;

public class AdminService {

	@Autowired
	AdminDao adminDao;

	public Admin save(Admin admin) {
		return adminDao.save(admin);
	}

	public Admin findById(Long id) {
		return adminDao.findById(id);
	}

	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	public void delete(Admin admin) {
		adminDao.delete(admin);
	}

	public Boolean authorize(Admin admin) {
		return adminDao.authorize(admin);
	}

}