package com.ideais.stock.system;

import static junit.framework.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Ignore
@ContextConfiguration({"classpath:spring/applicationContext.xml", "classpath:spring/dataSource.xml"})
@Transactional(propagation=Propagation.REQUIRED)
public class AbstractSystemTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private WebDriver driver;
	
	@Before
	public void setUp() {
		prepareDatabase();
	}
	
	private void prepareDatabase() {
		executeSqlScript("sql/prepareDatabase.sql", false);
	}

	protected WebDriver driver() {
		if (driver == null) {
			fail("Driver nulo.");
		}
		
		return driver;
	}

}
