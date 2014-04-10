package com.ideais.stock.dao;

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


@RunWith(SpringJUnit4ClassRunner.class)
public class SubcategoryDaoTest extends AbstractDaoTest {

	@Autowired
	private SubcategoryDao subcategoryDao;
	@Autowired
	private CategoryDao categoryDao;
	
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
		
		Subcategory savedSubcategory = subcategoryDao.save(subcategory);

		assertEquals(savedSubcategory.getId(), subcategory.getId());
	}
	
	@Test
	public void test_find_all() {
//		for (int i = 0; i < 10; i++) {
//			subcategoryDao.findAll();
//			System.out.println("hehe");
//		}
		assertEquals(1, subcategoryDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
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
		Subcategory savedSubcategory = subcategoryDao.findById(1L);
		String name = "Suspense";
		
		savedSubcategory.setName(name);
		subcategoryDao.save(savedSubcategory);
		
		Subcategory updatedSubcategory = subcategoryDao.findById(savedSubcategory.getId());
		
		assertEquals(name, updatedSubcategory.getName());
	}
	
	@Test
	public void test_delete() {
		subcategory = subcategoryDao.findById(1L);
		subcategory.getCategory().getSubcategories().remove(subcategory);
		
		subcategoryDao.delete(subcategory);
		
		assertEquals(0, subcategoryDao.findAll().size());
	}
	
	@Test
	public void create_with_existing_category() {
		category = categoryDao.findById(1L);
		
		if (category != null) {
			subcategory.setCategory(category);
			subcategory.setName("Tenis");
			category.getSubcategories().add(subcategory);
			category.setSubcategories(category.getSubcategories());
		}
		
		Subcategory savedSubcategory = subcategoryDao.save(subcategory);
		
		assertEquals(subcategory.getId(), savedSubcategory.getId());
		
	}
	
}