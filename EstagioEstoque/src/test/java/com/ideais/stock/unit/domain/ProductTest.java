package com.ideais.stock.unit.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.domain.Product;

@RunWith(JUnit4.class)
public class ProductTest {
	
	private Product product;
	
	@Before
	public void setUp() {
		product = new Product();
		product.setName("1984");
	}
	
	@Test
	public void softDelete() {
		product.setActive(true);
		product.softDelete();
		assertEquals(false, product.getActive());
	}
}
