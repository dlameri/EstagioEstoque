package com.ideais.stock.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.SubcategoryService;


@RunWith(SpringJUnit4ClassRunner.class)
public class SubcategoryDaoTest extends AbstractDaoTest {

	@Autowired
	private SubcategoryService subcategoryService;
	@Autowired
	private CategoryService categoryService;
	
	private Category category = new Category();
	private Subcategory subcategory = new Subcategory();
	private List<Subcategory> subcategories = new ArrayList<Subcategory>();
	
	@Test
	public void test_create() {
		Category category  = new Category();
		category.setName("Esportes");
		subcategory.setCategory(category);
		subcategory.setName("Tenis");
		subcategories.add(subcategory);
		category.setSubcategories(subcategories);
		
		Subcategory savedSubcategory = subcategoryService.save(subcategory);

		assertEquals(savedSubcategory.getId(), subcategory.getId());
	}
	
	@Test
	public void test_find_all() {
		Category category  = new Category();
		category.setName("Esportes");
		subcategory.setCategory(category);
		subcategory.setName("Tenis");
		subcategories.add(subcategory);
		category.setSubcategories(subcategories);
		
		Subcategory savedSubcategory = subcategoryService.save(subcategory);
		
		System.err.println(subcategoryService.findAll());
		
		subcategory.getCategory().getSubcategories().remove(subcategory);
		subcategoryService.delete(savedSubcategory);
		
		System.err.println(subcategoryService.findAll());
		
		assertEquals(1, subcategoryService.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Subcategory subcategory2 = subcategoryService.findById(1L);

		assertEquals(new Long(1), subcategory2.getId());
	}
	
	@Test
	public void test_update() {
		Subcategory savedSubcategory = subcategoryService.findById(1L);
		String name = "Suspense";
		
		savedSubcategory.setName(name);
		subcategoryService.save(savedSubcategory);
		
		Subcategory updatedSubcategory = subcategoryService.findById(savedSubcategory.getId());
		
		assertEquals(name, updatedSubcategory.getName());
	}
	
	@Test
	public void test_delete() {
		subcategory = subcategoryService.findById(1L);
		subcategory.getCategory().getSubcategories().remove(subcategory);
		
		subcategoryService.delete(subcategory);
		
		assertEquals(0, subcategoryService.findAll().size());
	}
	
	@Test
	public void create_with_existing_category() {
		category = categoryService.findById(1L);
		
		if (category != null) {
			subcategory.setCategory(category);
			subcategory.setName("Tenis");
			category.getSubcategories().add(subcategory);
			category.setSubcategories(category.getSubcategories());
		}
		
		Subcategory savedSubcategory = subcategoryService.save(subcategory);
		
		assertEquals(subcategory.getId(), savedSubcategory.getId());
		
	}
	
}