package main.test.com.ideias.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import main.java.com.ideais.stock.dao.CategoryDao;
import main.java.com.ideais.stock.domain.Category;


@RunWith(JUnit4.class)
public class CategoryDaoTest {

	private CategoryDao categoryDao;

	@Before
	public void setUp() {
		this.categoryDao = new CategoryDao();
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
		test_create();
		assertEquals(1, categoryDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Category category = new Category();
		category.setName("Esportes");	

		Category category2 = categoryDao.findById(categoryDao.create(category));
		
		assertEquals(new Long(1), category2.getId());
	}
	
	@Test
	public void test_update() {
		Category category = new Category();
		category.setName("Esportes");	
		Long id = categoryDao.create(category);
		
		category.setName("Celulares");
		categoryDao.update(category);
		
		Category savedCategory = categoryDao.findById(id);
		
		assertEquals("Celulares", savedCategory.getName());
	}
	
	@Test
	public void test_delete_customer() {
		Category category = new Category();
		category.setName("Esportes");	
		categoryDao.create(category);
		
		categoryDao.delete(category);
		
		assertEquals( 0, categoryDao.findAll().size() );
	}
}