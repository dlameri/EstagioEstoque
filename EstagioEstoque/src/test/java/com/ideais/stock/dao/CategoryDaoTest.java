package com.ideais.stock.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.BaseService;


@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoTest extends AbstractDaoTest {

	@Autowired
	private BaseService<Category> categoryService;
	@Autowired
	private BaseService<Subcategory> subcategoryService;

	@Test
	public void test_create() {
		Category category = new Category();
		category.setName("Esportes");
		
		Category category2 = null;
		try {
			category2 = categoryService.save(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals( category2.getId(), category.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(1, categoryService.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Category category2 = categoryService.findById(1L);

		assertEquals(new Long(1), category2.getId());
	}
	
	@Test
	public void test_update() {
		Category category = categoryService.findById(1L);
		
		category.setName("Celulares");
		try {
			categoryService.save(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Category savedCategory = categoryService.findById(1L);
		assertEquals("Celulares", savedCategory.getName());
	}
	
	@Test
	public void test_delete() {
		Category category = categoryService.findById(1L);
		categoryService.delete(category);
		
		assertEquals( false, category.getActive() );
	}
	
}