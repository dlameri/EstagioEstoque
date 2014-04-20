package com.ideais.stock.integration.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Admin;
import com.ideais.stock.service.AdminService;


@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceTest extends AbstractServiceTest {

	private Admin admin;
	@Autowired
	private AdminService adminService;
	
	@Before
	public void setUp() {
		admin = new Admin();
		admin.setName("Admin2");
		admin.setEmail("admin2@teste.com");
		admin.setPassword("123");
		super.setUp();
	}
	
	@Test
	public void test_create() {
		Admin savedAdmin = adminService.save(admin);
		
		assertEquals( savedAdmin.getId(), admin.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(1, adminService.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Admin admin = adminService.findById(1L); 
		
		assertEquals(new Long(1), admin.getId());
	}
	
	@Test
	public void test_update() {
		Admin savedAdmin = adminService.findById(1L);
		
		savedAdmin.setName("adminAlterado");
		adminService.save(savedAdmin);
		
		Admin updatedAdmin = adminService.findById(savedAdmin.getId());
		
		assertEquals("adminAlterado", updatedAdmin.getName());
	}
	
	@Test
	public void test_delete() {
		admin = adminService.findById(1L);
		adminService.delete(admin);
		
		assertEquals( 0, adminService.findAll().size() );
	}
	
	@Test
	public void test_authorize_invalid_email() {
		Admin admin = new Admin();
		admin.setEmail("");
		admin.setPassword("123");
		
		assertEquals(false, adminService.authorize(admin));
	}
	
	@Test
	public void test_authorize_invalid_password() {
		Admin admin = new Admin();
		admin.setEmail("admin@teste.com");
		admin.setPassword("");
		
		assertEquals(false, adminService.authorize(admin));
	}
	
	@Test
	public void test_authorize_invalid_email_and_password() {
		Admin admin = new Admin();
		admin.setEmail("admin@teste.com");
		admin.setPassword("321");
		
		assertEquals(false, adminService.authorize(admin));
	}
	
	@Test
	public void test_autorized_success() {
		Admin admin = new Admin();
		admin.setEmail("admin@teste.com");
		admin.setPassword("123");
		
		assertEquals(true, adminService.authorize(admin));
	}
	
}