package com.ideais.stock.unit.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.domain.Category;

@RunWith(JUnit4.class)
public class CategoryTest {
	
	Category category;
	
	@Before
	public void setUp() {
		category = new Category();
		category.setName("Livros");
	}
	
	@Test
	public void softDelete() {
		category.setActive(true);
		category.softDelete();
		
		assertEquals(false, category.getActive());
	}

}
