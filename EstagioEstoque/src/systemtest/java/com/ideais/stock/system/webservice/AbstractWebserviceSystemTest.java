package com.ideais.stock.system.webservice;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@Ignore
@ContextConfiguration("classpath:spring/systemTestContext.xml")
public class AbstractWebserviceSystemTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Before
	public void setUpEnviroment() {
		prepareDatabase();
	}
	
	private void prepareDatabase() {
		executeSqlScript("sql/systemTestPrepareDatabase.sql", false);
	}
}
