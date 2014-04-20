package com.ideais.stock.integration.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Category;
import com.ideais.stock.service.CategoryService;


@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryServiceTest extends AbstractServiceTest {

	@Autowired
	private CategoryService categoryService;
	private Category category;
	@Before
	public void setUp() {
		category = new Category();
		category.setName("Esportes");
		category.setActive(true);
		
		super.setUp();
	}
	
	@Test
	public void test_create() {
		category = categoryService.save(category);

		assertEquals("Esportes", category.getName());
	}
	
	@Test
	public void test_find_all() {
		assertEquals(10, categoryService.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		assertEquals(new Long(1), categoryService.findById(1L).getId());
	}
	
	@Test
	public void test_update() {
		category = categoryService.findById(1L);
		
		category.setName("Celulares");
		categoryService.save(category);
		Category savedCategory = categoryService.findById(1L);

		assertEquals("Celulares", savedCategory.getName());
	}
	
	@Test
	public void test_delete() {
		Category category = categoryService.findById(1L);
		categoryService.delete(category);

		category = categoryService.findById(1L);
		
		assertEquals( false, category.getActive() );
	}
	
}