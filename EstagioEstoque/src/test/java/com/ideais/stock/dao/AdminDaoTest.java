package com.ideais.stock.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.domain.Admin;


@RunWith(JUnit4.class)
public class AdminDaoTest {

	private Admin admin;
	private AdminDao adminDao;
	
	@Before
	public void setUp() {
		this.adminDao = new AdminDao();
		
		admin = new Admin();
		admin.setName("Admin");
		admin.setEmail("admin@teste.com");
		admin.setPassword("123");
	}
	
	@Test
	public void test_create() {
		Admin savedAdmin = adminDao.save(admin);
		
		assertEquals( savedAdmin.getId(), admin.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(0, adminDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Admin admin = adminDao.findById(1L); 
		
		assertEquals(new Long(1), admin.getId());
	}
	
	@Test
	public void test_update() {
		Admin savedAdmin = adminDao.save(admin);
		
		admin.setName("adminAlterado");
		adminDao.save(admin);
		
		Admin updatedAdmin = adminDao.findById(savedAdmin.getId());
		
		assertEquals("adminAlterado", updatedAdmin.getName());
	}
	
	@Test
	public void test_delete() {
		adminDao.save(admin);
		adminDao.delete(admin);
		
		assertEquals( 0, adminDao.findAll().size() );
	}
	
	@Test
	public void test_autorized_se_email_invalido() {
		adminDao.save(admin);
		String email = "";
		String password = "123";
		
		assertEquals(false, adminDao.authorized(email, password));
	}
	
	@Test
	public void test_autorized_se_password_invalido() {
		adminDao.save(admin);
		String email = "admin@teste.com";
		String password = "";
		
		assertEquals(false, adminDao.authorized(email, password));
	}
	
	@Test
	public void test_autorized_se_nao_encontrado() {
		adminDao.save(admin);
		String email = "admin@teste.com";
		String password = "321";
		
		assertEquals(false, adminDao.authorized(email, password));
	}
	
	@Test
	public void test_autorized_se_ok() {
		adminDao.save(admin);
		String email = "admin@teste.com";
		String password = "123";
		
		assertEquals(true, adminDao.authorized(email, password));
	}
	
}