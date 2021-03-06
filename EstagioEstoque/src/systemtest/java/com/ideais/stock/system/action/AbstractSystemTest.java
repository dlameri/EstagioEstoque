package com.ideais.stock.system.action;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@Ignore
@ContextConfiguration("classpath:spring/systemTestContext.xml")
public class AbstractSystemTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	@Before
	public void setUpEnviroment() {
		prepareDatabase();
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
	}
	
	@After
	public void closeFirefox() {
		driver.close();
	}
	
	private void prepareDatabase() {
		executeSqlScript("sql/systemTestPrepareDatabase.sql", false);
	}

	private WebElement findElement( By by ) {
		return driver.findElement(by);
	}
	
	protected String getTextByCss(String cssSelector) {
		return findElement( By.cssSelector(cssSelector) ).getText();
	}
	
	protected String getTextById(String id) {
		return findElement( By.id(id) ).getText();
	}
	
	protected void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			fail("Erro no sleep");
		}
	}
}
