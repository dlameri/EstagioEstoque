package com.ideais.stock.integration.webservice;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Ignore
@ContextConfiguration({"classpath:spring/integrationTestContext.xml", "classpath:spring/dataSource.xml"})
@TransactionConfiguration(defaultRollback=false)
@Transactional(propagation=Propagation.REQUIRED)
public class AbstractWebserviceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Before
	public void setUp() {
		prepareDatabase();
	}
	
	private void prepareDatabase() {
		executeSqlScript("sql/prepareDatabase.sql", false);
	}
}
