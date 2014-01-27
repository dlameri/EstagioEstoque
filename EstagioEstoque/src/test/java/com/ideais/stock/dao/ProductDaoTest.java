package com.ideais.stock.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.dao.ProductDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;



@RunWith(JUnit4.class)
public class ProductDaoTest {
	private ProductDao productDao;
	private Item item;
	private Product product;
	private Subcategory subcategory;
	private Category category;
	private Dimensions dimensions;
	private List<Image> images;
	
	@Before
	public void setUp() {
		this.productDao = new ProductDao();
		
		item = new Item();
		product = new Product();
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
		
		item.setName("Luva de boxe");
		item.setLongDescription("Uma descrição longa.");
		item.setShortDescription("Uma descrição curta.");
		item.setWeight(500000);
		item.setWarranty(36);
		item.setBrand("Paco Ideais");
		item.setModel("XTVZB-4435");
		item.setCategory(category);
		item.setSubcategory(subcategory);
		item.setDimensions(dimensions);
		
		product.setSku(01L);
		product.setOptionName("Cor");
		product.setOptionValue("Branca");
		product.setPriceFrom(1999.90);
		product.setPriceFor(19.90);
		product.setStock(9999);
		product.setItem(item);
		
		image.setProductUrl("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-e1-nx.m21al.019-intel-core-i34gb-500gb-windows-8-led-15-6-hdmi-135204700.jpg");
		image.setProduct(product);
		images.add(image);
		product.setImages(images);
	}
	
	@Test
	public void test_create() {
		Long id = productDao.create(product);
		
		assertEquals( id, product.getId() );
	}
	
	@Test
	public void test_find_all() {
		assertEquals(0, productDao.findAll().size());
	}
	
	@Test
	public void test_delete() {
		productDao.create(product);
		
		productDao.delete(product);
		
		assertEquals( 0, productDao.findAll().size() );
	}
}
