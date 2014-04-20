package com.ideais.stock.unit.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.domain.Subcategory;

@RunWith(JUnit4.class)
public class SubcategoryTest {
	
	private Subcategory subcategory;
	
	@Before
	public void setUp() {
		subcategory = new Subcategory();
		subcategory.setName("Suspense");
	}
	
	@Test
	public void softDelete() {
		subcategory.setActive(true);
		subcategory.softDelete();
		
		assertEquals(false, subcategory.getActive());
	}

}
