package com.ideais.stock.integration.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.dao.ImageDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;

@RunWith(SpringJUnit4ClassRunner.class)
public class ImageDaoTest extends AbstractDaoTest {
	
	@Autowired
	private ImageDao imageDao;
	private Image image;
	private Item item;
	private Subcategory subcategory;
	private Category category;
	private Dimensions dimensions;
	private Product product;
	
	@Before
	public void setUp() {
		image = new Image();
		item = new Item();
		product = new Product();
		subcategory = new Subcategory();
		category = new Category();
		dimensions = new Dimensions();
		
		dimensions.setDepth(10.);
		dimensions.setHeight(20.);
		dimensions.setWidth(30.);
		
		product.setName("Luva de boxe");
		product.setActive(true);
		product.setLongDescription("Uma descrição longa.");
		product.setShortDescription("Uma descrição curta.");
		product.setDimensions(dimensions);
		product.setWeight(500000);
		product.setWarranty(36);
		product.setBrand("Paco Ideais");
		product.setModel("XTVZB-4435");
		product.setActive(true);
		product.setRank(0);
		
		item.setSku(01L);
		item.setActive(true);
		item.setOptionName("Cor");
		item.setOptionValue("Branca");
		item.setPriceFrom(new BigDecimal (1999.90));
		item.setPriceFor(new BigDecimal (19.90));
		item.setStock(9999);
		item.setRank(0);
		item.setPromo(false);
		
		subcategory.setName("Luvas4");
		subcategory.setActive(true);
		
		category.setName("Esportes4");
		category.setActive(true);
		
		subcategory.setCategory(category);
		product.setCategory(category);
		product.setSubcategory(subcategory);
		item.setProduct(product);
	
		image.setShoppingCartUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setProductUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setAndroidProductUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setAndroidShowcaseUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setShowcaseUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setSuperzoomUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setItem(item);
		
		super.setUp();
	}
	
	@Test
	public void test_create() {
		Image savedImage = imageDao.save(image);
		
		assertEquals( new Long(2), savedImage.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(1, imageDao.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
		assertEquals(new Long(1), imageDao.findById(1L).getId());
	}
	
	@Test
	public void test_update() {
		image = imageDao.findById(1L);
		
		image.setProductUrl("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");
		imageDao.save(image);
		
		Image updatedImage = imageDao.findById(1L);
		
		assertEquals("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg", updatedImage.getProductUrl());
	}

}
