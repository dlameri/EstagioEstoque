package com.ideais.stock.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.ImageService;

@RunWith(JUnit4.class)
public class ImageDaoTest {
	
	@Autowired
	private ImageService imageService;
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
		
		item.setSku(01L);
		item.setActive(true);
		item.setOptionName("Cor");
		item.setOptionValue("Branca");
		item.setPriceFrom(new BigDecimal (1999.90));
		item.setPriceFor(new BigDecimal (19.90));
		item.setStock(9999);
		item.setRank(0);
		
		subcategory.setName("Luvas4");
		
		category.setName("Esportes4");
		
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
	}
	
	@Test
	public void test_create() {
		Image savedImage = imageService.save(image, true);
		
		assertEquals( savedImage.getId(), image.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(0, imageService.findAll().size());
	}
	
	@Test
	public void test_find_by_id() {
	    Image savedImage = imageService.save(image, true);
	    
		assertEquals(savedImage.getId(), imageService.findById(savedImage.getId()).getId());
	}
	
	@Test
	public void test_update() {
		Image savedImage = imageService.save(image, true);
		
		image.setProductUrl("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");
		imageService.save(image, true);
		
		Image updatedImage = imageService.findById(savedImage.getId());
		
		assertEquals("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg", updatedImage.getProductUrl());
	}

}
