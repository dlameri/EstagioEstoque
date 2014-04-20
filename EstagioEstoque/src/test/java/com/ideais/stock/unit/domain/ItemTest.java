package com.ideais.stock.unit.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.domain.Item;

@RunWith(JUnit4.class)
public class ItemTest {
	
	Item item;
	
	@Before
	public void setUp() {
		item = new Item();
	}
	
	@Test
	public void softDelete() {
		item.setActive(true);
		item.softDelete();
		assertEquals(false, item.getActive());
	}
	
	@Test
	public void test_value_formatter() {
		BigDecimal value = new BigDecimal(1024.53);
		assertEquals("R$ 1.024,53" ,item.valueFormatter(value));
	}

}
