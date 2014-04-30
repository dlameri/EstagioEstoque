package com.ideais.stock.integration.webservice;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.json.ItemJSON;
import com.ideais.stock.webservice.ItemWS;

@RunWith(SpringJUnit4ClassRunner.class)
public class ItemWSTest extends AbstractWebserviceTest {

	@Autowired
	private ItemWS itemWS;
	private Product product;
	private Item item;
	private ItemJSON itemJSON;
	
	@Before
	public void setUp() {
		product = new Product();
		item = new Item();
		
		product.setId(1L);
		product.setName("Nome do produto");
		product.setPromo(false);
		
		item.setActive(true);
		item.setId(1L);
		item.setSku(12345L);
		item.setOptionName("Nome opcional");
		item.setOptionValue("Valor opcional");
		item.setPriceFor(new BigDecimal(199.90));
		item.setPriceFrom(new BigDecimal(299.90));
		item.setRank(0);
		item.setStock(0);
		
		item.setProduct(product);
		
		itemJSON = new ItemJSON(item);
		super.setUp();
	}
	
	@Test
	public void get_item_by_id() {
		assertEquals(itemJSON.getSku(), itemWS.getItemById(1L).getSku());
	}
	
	@Test
	public void get_items() {
		assertEquals(10, itemWS.getItems("id", "asc", true, "0", "10").size());
	}
	
	@Test
	public void get_imagess_by_item_id() {
		assertEquals(1, itemWS.getImagesByItemId(15L).size());
	}
	
}
