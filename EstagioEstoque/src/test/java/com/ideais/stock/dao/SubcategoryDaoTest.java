package com.ideais.stock.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;


@RunWith(JUnit4.class)
public class SubcategoryDaoTest {

	private SubcategoryDao subcategoryDao;
	private CategoryDao categoryDao;
	private Category category;
	private Subcategory subcategory;
	private List<Subcategory> subcategories;
	
	@Before
	public void setUp() {
		this.subcategoryDao = new SubcategoryDao();
		this.categoryDao = new CategoryDao();
		
		category = new Category();
		subcategory = new Subcategory();
		subcategories = new ArrayList<Subcategory>();
		
		category.setName("Esportes");
		
		subcategory.setCategory(category);
		subcategory.setName("Tenis");
		subcategories.add(subcategory);
		category.setSubcategories(subcategories);
		
	}
	
	@Test
	public void test_create() {
		Long id = subcategoryDao.create(subcategory);

		assertEquals(id, subcategory.getId());
	}
	
	@Test
	public void test_find_all() {
		assertEquals(0, subcategoryDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
//		Long id = subcategoryDao.create(subcategory);
		Subcategory subcategory2 = subcategoryDao.findById(1L);
		System.out.println(subcategory2);
		for (int i = 0; i < 10; i++) {
			subcategory2 = subcategoryDao.findById(1L);
			System.out.println(subcategory2);
		}		
		assertEquals(new Long(1), subcategory2.getId());
	}
	
	@Test
	public void test_update() {
		Long id = subcategoryDao.create(subcategory);
		
		subcategory.setName("Camisas");
		subcategoryDao.update(subcategory);
		
		Subcategory savedSubcategory = subcategoryDao.findById(id);
		
		assertEquals("Camisas", savedSubcategory.getName());
	}
	
	@Test
	public void test_delete() {
		subcategoryDao.create(subcategory);
		
		subcategoryDao.delete(subcategory);
		
		assertEquals(0, subcategoryDao.findAll().size());
	}
	
	@Test
	public void create_with_existing_category() {
		category = categoryDao.findByName("asfasf");
		
		if (category != null) {
			subcategory.setCategory(category);
			subcategory.setName("Tenis");
			category.getSubcategories().add(subcategory);
			category.setSubcategories(category.getSubcategories());
		}
		
		Long id = subcategoryDao.create(subcategory);
		
		assertEquals(subcategory.getId(), id);
		
	}
	
}