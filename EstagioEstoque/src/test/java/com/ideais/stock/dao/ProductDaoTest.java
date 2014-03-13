package com.ideais.stock.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.dao.ProductDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Subcategory;


@RunWith(JUnit4.class)
public class ProductDaoTest {

	private ProductDao productDao;
	private Subcategory subcategory;
	private Category category;
	private Dimensions dimensions;
	private Product product;
	@Before
	public void setUp() {
		this.productDao = new ProductDao();
		
		product = new Product();
		subcategory = new Subcategory();
		category = new Category();
		dimensions = new Dimensions();
		
		dimensions.setDepth(10.);
		dimensions.setHeight(20.);
		dimensions.setWidth(30.);
		
		product.setName("Luva de boxe");
		product.setLongDescription("Uma descrição longa.");
		product.setShortDescription("Uma descrição curta.");
		product.setDimensions(dimensions);
		product.setWeight(500000);
		product.setWarranty(36);
		product.setBrand("Paco Ideais");
		product.setModel("XTVZB-4435");
		
		subcategory.setName("Luvas");
		
		category.setName("Esportes");
		
		subcategory.setCategory(category);
		product.setCategory(category);
		product.setSubcategory(subcategory);
	}
	
	@Test
	public void test_create() {
		Long id = productDao.create(product);

		assertEquals( id, product.getId() );
	}
	
	@Test
	public void test_create_with_product() {
		Item item = new Item();

		item.setSku(01L);
		item.setOptionName("Cor");
		item.setOptionValue("Branca");
		item.setPriceFrom(new BigDecimal (1999.90));
		item.setPriceFor(new BigDecimal (19.90));
		item.setStock(9999);
		item.setProduct(product);

		Long id = productDao.create(product);

		assertEquals( id, product.getId() );
	}
	
	
	@Test
	public void test_delete() {
		productDao.create(product);
		
		productDao.delete(product);

		assertEquals( 0, productDao.findAll().size() );
	}
}