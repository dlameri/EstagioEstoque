package com.ideais.stock.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Admin;


public class AdminDao extends AbstractDao<Admin>{
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Admin save(Admin admin) {
		return super.save(admin);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Admin admin) {
		super.delete(admin);
	}
	
	public Admin findById(Long id) {
		return super.findById(Admin.class, id);
	}
	
	public List<Admin> findAll() {
		return super.findAll(Admin.class);
	}
	
	
	public Boolean authorize(Admin admin) {
		if (admin.getEmail() == null || admin.getEmail().isEmpty()) {
			return false;
		}
		
		if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
			return false;
		}
		
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.like("email", admin.getEmail()) );
		restrictions.add( Restrictions.like("password", admin.getPassword()) );
		
		List<Admin> admins  = super.findByRestrictions(Admin.class, restrictions);
		
		if (admins.size() == 1){
			return true;
		}
		
		return false;
	}
}