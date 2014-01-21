package com.ideais.stock.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.dao.ImageDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;

@RunWith(JUnit4.class)
public class ImageDaoTest {
	
	private ImageDao imageDao;
	private Image image;
	private Product product;
	private Subcategory subcategory;
	private Category category;
	private Dimensions dimensions;
	private Item item;
	
	@Before
	public void setUp() {
		this.imageDao = new ImageDao();
		
		image = new Image();
		product = new Product();
		item = new Item();
		subcategory = new Subcategory();
		category = new Category();
		dimensions = new Dimensions();
		
		dimensions.setDepth(10.);
		dimensions.setHeight(20.);
		dimensions.setWidth(30.);
		
		item.setName("Luva de boxe");
		item.setLongDescription("Uma descrição longa.");
		item.setShortDescription("Uma descrição curta.");
		item.setDimensions(dimensions);
		item.setWeight(500000);
		item.setWarranty(36);
		item.setBrand("Paco Ideais");
		item.setModel("XTVZB-4435");
		
		product.setSku(01L);
		product.setOptionName("Cor");
		product.setOptionValue("Branca");
		product.setPriceFrom(1999.90);
		product.setPriceFor(19.90);
		product.setStock(9999);
		
		subcategory.setName("Luvas");
		
		category.setName("Esportes");
		
		subcategory.setCategory(category);
		item.setCategory(category);
		item.setSubcategory(subcategory);
		product.setItem(item);
	
		image.setProductUrl("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");
		image.setProduct(product);
	}
	
	@Test
	public void test_create() {
		Long id = imageDao.create(image);
		
		assertEquals( id, image.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(0, imageDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Image image2 = imageDao.findById(imageDao.create(image));
		
		assertEquals(new Long(1), image2.getId());
	}
	
	@Test
	public void test_update() {
		Long id = imageDao.create(image);
		
		image.setProductUrl("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");
		imageDao.update(image);
		
		Image savedImage = imageDao.findById(id);
		
		assertEquals("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg", savedImage.getProductUrl());
	}
	
	@Test
	public void test_delete() {
		imageDao.create(image);
		
		imageDao.delete(image);
		
		assertEquals( 0, imageDao.findAll().size() );
	}

}
