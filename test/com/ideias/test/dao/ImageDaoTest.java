package com.ideias.test.dao;

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
	
	@Before
	public void setUp() {
		this.imageDao = new ImageDao();
	}
	
	@Test
	public void test_create() {
		Image image = new Image();
		Product product = new Product();
		Item item = new Item();
		Subcategory subcategory = new Subcategory();
		Category category = new Category();
		
		item.setName("Luva de boxe");
		item.setLongDescription("Uma descrição longa.");
		item.setShortDescription("Uma descrição curta.");
		item.setDimensions(new Dimensions(50., 50., 150.));
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
		item.setSubcategory(subcategory);
		product.setItem(item);
	
		image.setPath("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");
		image.setProduct(product);
		
		Long id = imageDao.create(image);
		
		assertEquals( id, image.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(0, imageDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		Image image = new Image();
		image.setPath("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");	

		Image image2 = imageDao.findById(imageDao.create(image));
		
		assertEquals(new Long(1), image2.getId());
	}
	
	@Test
	public void test_update() {
		Image image = new Image();
		image.setPath("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");	
		Long id = imageDao.create(image);
		
		image.setPath("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");
		imageDao.update(image);
		
		Image savedImage = imageDao.findById(id);
		
		assertEquals("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg", savedImage.getPath());
	}
	
	@Test
	public void test_delete_Image() {
		Image image = new Image();
		image.setPath("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");	
		imageDao.create(image);
		
		imageDao.delete(image);
		
		assertEquals( 0, imageDao.findAll().size() );
	}

}
