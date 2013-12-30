package com.ideias.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.dao.ItemDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Subcategory;


@RunWith(JUnit4.class)
public class ItemDaoTest {

	private ItemDao itemDao;

	@Before
	public void setUp() {
		this.itemDao = new ItemDao();
	}
	
	@Test
	public void test_create() {
		Item item = new Item();
		Subcategory subcategory = new Subcategory();
		Category category = new Category();
		
		item.setName("Luva de boxe");
		item.setLongDescription("Uma descrição longa.");
		item.setShortDescription("Uma descrição curta.");
		item.setDimensions("50x50x150");
		item.setWeight(500000);
		item.setWarranty(36);
		item.setBrand("Paco Ideais");
		item.setModel("XTVZB-4435");
		
		subcategory.setName("Luvas");
		
		category.setName("Esportes");
		
		subcategory.setCategory(category);
		item.setSubcategory(subcategory);
		
		Long id = itemDao.create(item);
		assertEquals( id, item.getId() );
	}
}