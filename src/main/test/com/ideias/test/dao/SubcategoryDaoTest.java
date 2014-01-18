package main.test.com.ideias.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import main.java.com.ideais.stock.dao.SubcategoryDao;
import main.java.com.ideais.stock.domain.Category;
import main.java.com.ideais.stock.domain.Subcategory;


@RunWith(JUnit4.class)
public class SubcategoryDaoTest {

	private SubcategoryDao subcategoryDao;
	
	@Before
	public void setUp() {
		this.subcategoryDao = new SubcategoryDao();
	}
	
	@Test
	public void test_create() {
		Category category = new Category();
		Subcategory subcategory = new Subcategory();
		
		category.setName("Esportes");
		subcategory.setCategory(category);
		subcategory.setName("Tenis");
		
		
		Long id = subcategoryDao.create(subcategory);
		assertEquals(id, subcategory.getId());
	}
	
	@Test
	public void test_find_all() {
		assertEquals(0, subcategoryDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Category category = new Category();
		Subcategory subcategory = new Subcategory();
		
		category.setName("Esportes");
		subcategory.setCategory(category);
		subcategory.setName("Tenis");
		
		Long id = subcategoryDao.create(subcategory);
		Subcategory subcategory2 = subcategoryDao.findById(id);
		
		assertEquals(new Long(1), subcategory2.getId());
	}
	
	@Test
	public void test_update() {
		Category category = new Category();
		Subcategory subcategory = new Subcategory();
		
		category.setName("Esportes");
		subcategory.setCategory(category);
		subcategory.setName("Tenis");
		
		Long id = subcategoryDao.create(subcategory);
		
		subcategory.setName("Camisas");
		subcategoryDao.update(subcategory);
		
		Subcategory savedSubcategory = subcategoryDao.findById(id);
		
		assertEquals("Camisas", savedSubcategory.getName());
	}
}