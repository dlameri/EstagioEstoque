package com.ideais.stock.integration.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.dao.SubcategoryDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.CategoryService;


@RunWith(SpringJUnit4ClassRunner.class)
public class SubcategoryDaoTest extends AbstractDaoTest {

	@Autowired
	private SubcategoryDao subcategoryDao;
	@Autowired
	private CategoryService categoryService;
	
	private Category category;
	private Subcategory subcategory;
	private List<Subcategory> subcategories = new ArrayList<Subcategory>();
	
	@Before
	public void setUp() {
		category  = new Category();
		subcategory = new Subcategory();
		
		category.setActive(true);
		category.setName("Esportes");

		subcategory.setActive(true);
		subcategory.setCategory(category);
		subcategory.setName("Tenis");
		subcategories.add(subcategory);
		category.setSubcategories(subcategories);
		
		super.setUp();
	}
	
	
	@Test
	public void test_create() {
		subcategory = subcategoryDao.save(subcategory);

		assertEquals("Tenis", subcategory.getName());
	}
	
	@Test
	public void test_find_all() {
		assertEquals(10, subcategoryDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		assertEquals(new Long(1), subcategoryDao.findById(1L).getId());
	}
	
	@Test
	public void test_update() {
		subcategory = subcategoryDao.findById(1L);
		String name = "Suspense";
		
		subcategory.setName(name);
		subcategoryDao.save(subcategory);
		
		Subcategory updatedSubcategory = subcategoryDao.findById(subcategory.getId());
		
		assertEquals(name, updatedSubcategory.getName());
	}
	
	@Test
	public void test_delete() {
		subcategory = subcategoryDao.findById(1L);
		subcategoryDao.delete(subcategory);
		
		assertEquals(9, subcategoryDao.findAll().size());
	}
	
	@Test
	public void create_with_existing_category() {
		category = categoryService.findById(1L);
		
		if (category != null) {
			subcategory.setCategory(category);
			subcategory.setName("Tenis2");
			category.getSubcategories().add(subcategory);
			category.setSubcategories(category.getSubcategories());
		}
		
		subcategory = subcategoryDao.save(subcategory);
		
		assertEquals("Tenis2", subcategory.getName());
		
	}
	
}