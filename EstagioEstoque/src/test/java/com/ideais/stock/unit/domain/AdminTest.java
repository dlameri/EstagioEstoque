package com.ideais.stock.unit.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ideais.stock.domain.Admin;

@RunWith(JUnit4.class)
public class AdminTest {

	private Admin admin;

	@Before
	public void setUp() {
		admin = new Admin();
		admin.setPassword("123");
	}

	@Test
	public void make_secure_password_test() {
		assertEquals(
				"98f97621dc9308ce4496edd3ee32c6d583c54f0e8368626697dd6de1daa98675576aba57e92220ef08b89f30e2cbafd8a92646bf5fa92a395d4c1e7133181986",
				admin.getPassword());
	}

}
