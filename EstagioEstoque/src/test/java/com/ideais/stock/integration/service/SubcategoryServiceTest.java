package com.ideais.stock.integration.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.SubcategoryService;


@RunWith(SpringJUnit4ClassRunner.class)
public class SubcategoryServiceTest extends AbstractServiceTest {

	@Autowired
	private SubcategoryService subcategoryService;
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

		subcategory.setCategory(category);
		subcategory.setName("Tenis");
		subcategories.add(subcategory);
		category.setSubcategories(subcategories);
		
		super.setUp();
	}
	
	
	@Test
	public void test_create() {
		subcategory = subcategoryService.save(subcategory);

		assertEquals("Tenis", subcategory.getName());
	}
	
	@Test
	public void test_find_all() {
		assertEquals(10, subcategoryService.findAll(true).size());
	}
	
	@Test
	public void test_find_by_id() {
		assertEquals(new Long(1), subcategoryService.findById(1L).getId());
	}
	
	@Test
	public void test_update() {
		subcategory = subcategoryService.findById(1L);
		String name = "Suspense";
		
		subcategory.setName(name);
		subcategoryService.save(subcategory);
		
		Subcategory updatedSubcategory = subcategoryService.findById(subcategory.getId());
		
		assertEquals(name, updatedSubcategory.getName());
	}
	
	@Test
	public void test_delete() {
		subcategory = subcategoryService.findById(1L);
		subcategoryService.delete(subcategory);
		
		subcategory = subcategoryService.findById(1L);
		
		assertEquals(false, subcategory.getActive());
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
		
		subcategory = subcategoryService.save(subcategory);
		
		assertEquals("Tenis2", subcategory.getName());
		
	}
	
}