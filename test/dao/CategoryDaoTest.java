package dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import estoque.dao.CategoryDao;
import estoque.domain.Category;


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
	
//	@Test
//	public void test_find_all_categories() {
//		List<Category> category = categoryDao.findAll();
//		assertEquals(0, category.size());
//	}
	
}