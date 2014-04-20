package com.ideais.stock.integration.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.ItemService;



@RunWith(SpringJUnit4ClassRunner.class)
public class ItemServiceTest extends AbstractServiceTest {
	
	@Autowired
	private ItemService itemService;
	private Product product;
	private Item item;
	private Subcategory subcategory;
	private Category category;
	private Dimensions dimensions;
	private List<Image> images;
	private List<Item> items;
	
	@Before
	public void setUp() {
		items = new ArrayList<Item>();
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
		category.setActive(true);
		
		subcategory.setName("Luvas");
		subcategory.setCategory(category);
		subcategory.setActive(true);
		
		product.setName("Luva de boxe");
		product.setActive(true);
		product.setLongDescription("Uma descrição longa.");
		product.setShortDescription("Uma descrição curta.");
		product.setWeight(500000);
		product.setWarranty(36);
		product.setBrand("Paco Ideais");
		product.setModel("XTVZB-4435");
		product.setCategory(category);
		product.setSubcategory(subcategory);
		product.setDimensions(dimensions);
		product.setRank(0);
		
		item.setSku(01L);
		item.setActive(true);
		item.setOptionName("Cor");
		item.setOptionValue("Branca");
		item.setPriceFrom(new BigDecimal (1999.90));
		item.setPriceFor(new BigDecimal (19.90));
		item.setStock(9999);
		item.setPromo(false);
		item.setProduct(product);
		
		items.add(item);
		
		image.setMain(true);
		image.setShoppingCartUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setProductUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setAndroidProductUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setAndroidShowcaseUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setShowcaseUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setSuperzoomUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
		image.setItem(item);
		images.add(image);
		product.setItems(items);
		
		super.setUp();
	}
	
	@Test
	public void test_create() {
		item = itemService.save(item);

		assertEquals( new Long(1), item.getSku() );
	}
	
	@Test
	public void test_find_by_id() {
		assertEquals(new Long(1), itemService.findById(1L).getId());
	}
	
	@Test
	public void test_find_all() {
		assertEquals(16, itemService.findAll().size());
	}
	
	@Test
	public void delete() {
		item = itemService.findById(1L);
		itemService.delete(item);
		item = itemService.findById(1L);

		assertEquals(false, item.getActive());
	}
}
