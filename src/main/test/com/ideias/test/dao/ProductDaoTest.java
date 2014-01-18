package main.test.com.ideias.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import main.java.com.ideais.stock.dao.ProductDao;
import main.java.com.ideais.stock.domain.Category;
import main.java.com.ideais.stock.domain.Dimensions;
import main.java.com.ideais.stock.domain.Item;
import main.java.com.ideais.stock.domain.Product;
import main.java.com.ideais.stock.domain.Subcategory;


@RunWith(JUnit4.class)
public class ProductDaoTest {
	private ProductDao productDao;
	
	@Before
	public void setUp() {
		this.productDao = new ProductDao();
	}
	
	@Test
	public void create() {
		Item item = new Item();
		Product product = new Product();
		Subcategory subcategory = new Subcategory();
		Category category = new Category();
		Dimensions dimencions = new Dimensions();
		
		dimencions.setDepth(10.);
		dimencions.setHeight(20.);
		dimencions.setWidth(30.);
		
		item.setName("Luva de boxe");
		item.setLongDescription("Uma descrição longa.");
		item.setShortDescription("Uma descrição curta.");
		item.setDimensions(dimencions);
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
		
		Long id = productDao.create(product);
		
		assertEquals( id, item.getId() );
	}
}
