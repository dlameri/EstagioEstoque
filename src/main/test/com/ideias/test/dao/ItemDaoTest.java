package main.test.com.ideias.test.dao;

import static org.junit.Assert.assertEquals;
import main.java.com.ideais.stock.dao.ItemDao;
import main.java.com.ideais.stock.domain.Category;
import main.java.com.ideais.stock.domain.Dimensions;
import main.java.com.ideais.stock.domain.Item;
import main.java.com.ideais.stock.domain.Subcategory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class ItemDaoTest {

	private ItemDao itemDao;
	private Subcategory subcategory;
	private Category category;
	private Dimensions dimensions;
	private Item item;
	@Before
	public void setUp() {
		this.itemDao = new ItemDao();
		
		item = new Item();
		subcategory = new Subcategory();
		category = new Category();
		dimensions = new Dimensions();
		
		dimensions.setDepth(10.);
		dimensions.setHeight(20.);
		dimensions.setWidth(30.);
		
		item.setName("Luva de boxe");
		item.setLongDescription("Uma descrição longa.");
		item.setShortDescription("Uma descrição curta.");
		item.setDimensions(dimensions);
		item.setWeight(500000);
		item.setWarranty(36);
		item.setBrand("Paco Ideais");
		item.setModel("XTVZB-4435");
		
		subcategory.setName("Luvas");
		
		category.setName("Esportes");
		
		subcategory.setCategory(category);
		item.setCategory(category);
		item.setSubcategory(subcategory);
	}
	
	@Test
	public void test_create() {
		Long id = itemDao.create(item);

		assertEquals( id, item.getId() );
	}
	
	@Test
	public void test_delete() {
		itemDao.create(item);
		
		itemDao.delete(item);

		assertEquals( 0, itemDao.findAll().size() );
	}
}