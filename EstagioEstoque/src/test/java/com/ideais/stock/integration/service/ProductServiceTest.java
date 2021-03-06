package com.ideais.stock.integration.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest extends AbstractServiceTest {

	@Autowired
	private ProductService productService;
	private Subcategory subcategory;
	private Category category;
	private Dimensions dimensions;
	private Product product;
	
	@Before
	public void setUp() {
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
		category.setActive(true);
		
		subcategory.setCategory(category);
		subcategory.setActive(true);
		product.setCategory(subcategory.getCategory());
		product.setSubcategory(subcategory);
		
		super.setUp();
	}
	
	@Test
	public void test_create() {
		product = productService.save(product);
		assertEquals("Luva de boxe", product.getName());
	}
	
	@Test
	public void test_create_with_item() {
		Item item = new Item();

		item.setSku(01L);
		item.setOptionName("Cor");
		item.setOptionValue("Branca");
		item.setPriceFrom(new BigDecimal (1999.90));
		item.setPriceFor(new BigDecimal (19.90));
		item.setStock(9999);
		item.setProduct(product);

		product = productService.save(product);

		assertEquals("Luva de boxe", product.getName());
	}
	
	
	@Test
	public void test_delete() {
		product = productService.findById(1L);
		productService.delete(product);

		product = productService.findById(1L);
		
		assertEquals( false, product.getActive());
	}
	
	@Test
	public void find_by_id() {
		Product product = productService.findById(1L);
		
		assertEquals(new Long(1), product.getId());
	}
}