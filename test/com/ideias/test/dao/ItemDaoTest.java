package com.ideias.test.dao;
//package dao;
//
//import static org.junit.Assert.assertEquals;
//
//import javax.persistence.Column;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//import estoque.dao.ItemDao;
//import estoque.domain.Category;
//import estoque.domain.Item;
//import estoque.domain.Product;
//import estoque.domain.Subcategory;
//
//
//@RunWith(JUnit4.class)
//public class ItemDaoTest {
//
//	private ItemDao itemDao;
//
//	@Before
//	public void setUp() {
//		this.itemDao = new ItemDao();
//	}
//	
//	@Test
//	public void test_create() {
//		Item item = new Item();
//		Product product = new Product();
//		Subcategory subcategory = new Subcategory();
//		Category category = new Category();
//		
//		item.setName("Luva de boxe");
//		item.setLongDescription("Uma descrição longa.");
//		item.setShortDescription("Uma descrição curta.");
//		item.setDimensions("50x50x150");
//		item.setWeight(500000);
//		item.setWarranty(36);
//		item.setBrand("Paco Ideais");
//		item.setModel("XTVZB-4435");
//		
//		product.setOptionName("Cor");
//		product.setOptionValue("Branca");
//		product.setPriceFrom(1999.90);
//		product.setPriceFor(19.90);
//		product.setStock(9999);
//		
//		category.setName("Esportes");
//		
//		subcategory.setName("Luvas");
//		
//		product.setItem(item);
//		item.setProduct(product);
//		item.setSubcategory(subcategory);
//		subcategory.setItem(item);
//		
//		Long id = ItemDao.create(item);
//		assertEquals( id, item.getId() );
//	}
//	
//}