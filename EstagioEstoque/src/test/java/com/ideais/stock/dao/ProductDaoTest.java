package com.ideais.stock.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.dao.ItemDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Subcategory;



@RunWith(JUnit4.class)
public class ProductDaoTest {
	private ItemDao itemDao;
	private Product product;
	private Item item;
	private Subcategory subcategory;
	private Category category;
	private Dimensions dimensions;
	private List<Image> images;
	
	@Before
	public void setUp() {
		this.itemDao = new ItemDao();
		
		product = new Product();
		item = new Item();
		subcategory = new Subcategory();
		category = new Category();
		dimensions = new Dimensions();
		images = new ArrayList<Image>();
		Image image = new Image();
		
		dimensions.setDepth(10.);
		dimensions.setHeight(20.);
		dimensions.setWidth(30.);
		
		category.setName("Luta");

		subcategory.setName("Luvas");
		subcategory.setCategory(category);
		
		product.setName("Luva de boxe");
		product.setLongDescription("Uma descrição longa.");
		product.setShortDescription("Uma descrição curta.");
		product.setWeight(500000);
		product.setWarranty(36);
		product.setBrand("Paco Ideais");
		product.setModel("XTVZB-4435");
		product.setCategory(category);
		product.setSubcategory(subcategory);
		product.setDimensions(dimensions);
		
		item.setSku(01L);
		item.setOptionName("Cor");
		item.setOptionValue("Branca");
		item.setPriceFrom(new BigDecimal (1999.90));
		item.setPriceFor(new BigDecimal (19.90));
		item.setStock(9999);
		item.setProduct(product);
		
		image.setProductUrl("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");
		image.setItem(item);
		images.add(image);
		item.setImages(images);
	}
	
	@Test
	public void test_create() {
		Long id = itemDao.create(item);
		
		assertEquals( id, item.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(0, itemDao.findAll().size());
	}
	
	@Test
	public void test_delete() {
		itemDao.create(item);
		
		itemDao.delete(item);
		
		assertEquals( 0, itemDao.findAll().size() );
	}
}
