package com.ideais.stock.integration.webservice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Category;
import com.ideais.stock.json.CategoryJSON;
import com.ideais.stock.webservice.CategoryWS;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryWSTest extends AbstractWebserviceTest {

	@Autowired
	private CategoryWS categoryWS;
	private Category category;
	private CategoryJSON categoryJSON;
	
	@Before
	public void setUp() {
		category = new Category();
		category.setActive(true);
		category.setId(1L);
		category.setName("Categoria 1");
		
		categoryJSON = new CategoryJSON(category);
		super.setUp();
	}
	
	@Test
	public void get_category_by_id() {
		assertEquals(categoryJSON.getName(), categoryWS.getCategoryById(1L).getName());
	}
	
	@Test
	public void get_categories() {
		assertEquals(10, categoryWS.getCategories().size());
	}
	
	@Test
	public void get_subcategories_by_category_id() {
		assertEquals(4, categoryWS.getSubcategoriesByCategoryId(1L).size());
	}
	
	@Test
	public void get_products_by_category_id() {
		assertEquals(3, categoryWS.getProductsByCategoryId(1L, "id", "asc", true, "0", "20").size());
	}
	
	@Test
	public void get_top_products_by_category_id() {
		assertEquals(1, categoryWS.getTopProductsByCategoryId(1L, "id", "asc", true, "0", "1").size());
	}
	
}
