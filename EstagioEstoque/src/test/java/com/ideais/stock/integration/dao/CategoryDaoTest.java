package com.ideais.stock.integration.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.domain.Category;


@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoTest extends AbstractDaoTest {

	@Autowired
	private CategoryDao categoryDao;
	Category category;
	
	@Before
	public void setUp() {
		category = new Category();
		category.setName("Esportes");
		category.setActive(true);
		
		super.setUp();
	}
	
	@Test
	public void test_create() {
		category = categoryDao.save(category);

		assertEquals("Esportes", category.getName() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(10, categoryDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		assertEquals(new Long(1), categoryDao.findById(1L).getId());
	}
	
	@Test
	public void test_update() {
		category = categoryDao.findById(1L);
		
		category.setName("Celulares");
		categoryDao.save(category);
		Category savedCategory = categoryDao.findById(1L);

		assertEquals("Celulares", savedCategory.getName());
	}
}