package com.ideais.stock.integration.webservice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Product;
import com.ideais.stock.json.ProductJSON;
import com.ideais.stock.webservice.ProductWS;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductWSTest extends AbstractWebserviceTest {

	@Autowired
	private ProductWS productWS;
	private Product product;
	private Dimensions dimensions;
	private ProductJSON productJSON;
	
	@Before
	public void setUp() {
		product = new Product();
		dimensions = new Dimensions();

		dimensions.setDepth(10.);
		dimensions.setHeight(30.);
		dimensions.setWidth(10.);
		
		product.setActive(true);
		product.setId(1L);
		product.setName("Nome do produto");
		product.setBrand("Nome da Marca");
		product.setLongDescription("Descrição longa");
		product.setShortDescription("Descrição curta");
		product.setModel("Nome do modelo");
		product.setRank(0);
		product.setWarranty(0);
		product.setWeight(0);
		
		product.setDimensions(dimensions);
		
		productJSON = new ProductJSON(product);
		super.setUp();
	}
	
	@Test
	public void get_product_by_id() {
		assertEquals(productJSON.getId(), productWS.getProductById(1L).getId());
	}
	
	@Test
	public void get_products() {
		assertEquals(8, productWS.getProducts("id", "asc", true, "0", "10", true).size());
	}
	
	@Test
	public void get_items_by_product_id() {
		assertEquals(3, productWS.getItemsByProductId(1L).size());
	}
	
	@Test
	public void get_dimensions_by_product_id() {
		assertEquals(new Double(10), productWS.getDimensionsByProductId(1L).getDepth());
	}
	
}
