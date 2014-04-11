package com.ideais.stock.dao;

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

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductDaoTest extends AbstractDaoTest {

	@Autowired
	private ProductDao productDao;
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
		
		subcategory.setCategory(category);
		product.setCategory(subcategory.getCategory());
		product.setSubcategory(subcategory);
		
		super.setUp();
	}
	
	@Test
	public void test_create() {
		Product savedProduct = productDao.save(product);

		assertEquals( savedProduct.getId(), product.getId() );
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

		Product savedProduct = productDao.save(product);

		assertEquals( savedProduct.getId(), product.getId() );
	}
	
	
	@Test
	public void test_delete() {
		product = productDao.findById(1L);
		
		productDao.delete(product);

		assertEquals( 0, productDao.findAll().size() );
	}
	
	@Test
	public void find_by_id() {
		Product product = productDao.findById(1L);
		
		assertEquals(new Long(1), product.getId());
	}
}