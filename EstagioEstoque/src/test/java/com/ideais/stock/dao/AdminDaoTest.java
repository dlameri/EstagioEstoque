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
		Long id = adminDao.create(admin);
		
		assertEquals( id, admin.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(0, adminDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Admin adminId = adminDao.findById(adminDao.create(admin));
		
		assertEquals(new Long(1), adminId.getId());
	}
	
	@Test
	public void test_update() {
		Long id = adminDao.create(admin);
		
		admin.setName("adminAlterado");
		adminDao.update(admin);
		
		Admin savedAdmin = adminDao.findById(id);
		
		assertEquals("adminAlterado", savedAdmin.getName());
	}
	
	@Test
	public void test_delete() {
		adminDao.create(admin);
		adminDao.delete(admin);
		
		assertEquals( 0, adminDao.findAll().size() );
	}
	
	@Test
	public void test_autorized_se_email_invalido() {
		adminDao.create(admin);
		String email = "";
		String password = "123";
		
		assertEquals(false, adminDao.authorized(email, password));
	}
	
	@Test
	public void test_autorized_se_password_invalido() {
		adminDao.create(admin);
		String email = "admin@teste.com";
		String password = "";
		
		assertEquals(false, adminDao.authorized(email, password));
	}
	
	@Test
	public void test_autorized_se_nao_encontrado() {
		adminDao.create(admin);
		String email = "admin@teste.com";
		String password = "321";
		
		assertEquals(false, adminDao.authorized(email, password));
	}
	
	@Test
	public void test_autorized_se_ok() {
		adminDao.create(admin);
		String email = "admin@teste.com";
		String password = "123";
		
		assertEquals(true, adminDao.authorized(email, password));
	}
	
}