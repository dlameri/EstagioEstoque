package com.ideais.stock.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.domain.Category;


@RunWith(JUnit4.class)
public class CategoryDaoTest extends AbstractDaoTest {

	private CategoryDao categoryDao;

	@Before
	public void setUp() {
		this.categoryDao = new CategoryDao();
		super.setUp(categoryDao.getSessionFactory());
	}
	
	@Test
	public void test_create() {
		Category category = new Category();
		category.setName("Esportes");
		
		Long id = categoryDao.create(category);
		assertEquals( id, category.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(1, categoryDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Category category2 = categoryDao.findById(1L);
		
		assertEquals(new Long(1), category2.getId());
	}
	
	@Test
	public void test_update() {
		Category category = categoryDao.findById(1L);
		
		category.setName("Celulares");
		categoryDao.update(category);
		
		Category savedCategory = categoryDao.findById(1L);
		assertEquals("Celulares", savedCategory.getName());
	}
	
	@Test
	public void test_delete() {
		categoryDao.delete(categoryDao.findById(1L));
		
		assertEquals( 0, categoryDao.findAll().size() );
	}
	
	@Test
	public void test_find_by_name() {
		String name = "HISTORIA";
		
		assertEquals(name, categoryDao.findByName(name).getName());
	}
}