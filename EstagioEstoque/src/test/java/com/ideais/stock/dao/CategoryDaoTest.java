package com.ideais.stock.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Category;


@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoTest extends AbstractDaoTest {

	@Autowired
	private CategoryDao categoryDao;

	@Test
	public void test_create() {
		Category category = new Category();
		category.setName("Esportes");
		
		Category category2 = categoryDao.create(category);
		assertEquals( category2.getId(), category.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(1, categoryDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Category category2 = categoryDao.findById(1L);
		System.out.println(category2.getName());
		for (int i = 0; i < 10; i++) {
			category2 = categoryDao.findById(1L);
			System.out.println(category2.getName());
		}
		
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