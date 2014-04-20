package com.ideais.stock.integration.webservice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.webservice.SubcategoryWS;

@RunWith(SpringJUnit4ClassRunner.class)
public class SubcategoryWSTest extends AbstractWebserviceTest {

	@Autowired
	private SubcategoryWS subcategoryWS;
	private Subcategory subcategory;
	private SubcategoryJSON subcategoryJSON;
	
	@Before
	public void setUp() {
		subcategory = new Subcategory();
		subcategory.setActive(true);
		subcategory.setId(1L);
		subcategory.setName("Subcategoria 1");
		
		subcategoryJSON = new SubcategoryJSON(subcategory);
		super.setUp();
	}
	
	@Test
	public void get_subcategory_by_id() {
		assertEquals(subcategoryJSON.getName(), subcategoryWS.getSubcategoryById(1L).getName());
	}
	
	@Test
	public void get_subcategories() {
		assertEquals(10, subcategoryWS.getSubcategories().size());
	}
	
	@Test
	public void get_products_by_category_id() {
		assertEquals(2, subcategoryWS.getProductsBySubcategoryId(2L).size());
	}
	
}
