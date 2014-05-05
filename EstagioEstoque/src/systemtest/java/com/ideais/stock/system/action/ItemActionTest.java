package com.ideais.stock.system.action;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ItemActionTest extends AbstractSystemTest implements SystemTestBehavior {

	
	@Before
	public void setUp() {
		driver.get("http://localhost:8080/EstagioEstoque/web/");
		driver.findElement(By.name("email")).submit();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='produtos']")));
		driver.findElement(By.xpath("//a[@href='produtos']")).click();
		
		//Abrir jTable de Item
		wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@data-record-key='1']/td/img"))));
		driver.findElement(By.xpath("//tr[@data-record-key='1']/td/img")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("jtable-delete-command-button")));
	}
	
	@Test
	public void create() {
		driver.findElements(By.className("jtable-toolbar-item-add-record")).get(1).click();
		
		wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("item.active"))));
		driver.findElement(By.name("item.priceFrom")).sendKeys("100.50");
		driver.findElement(By.name("item.priceFor")).sendKeys("100.49");
		driver.findElement(By.name("item.optionName")).sendKeys("Option Name");
		driver.findElement(By.name("item.optionValue")).sendKeys("Option Value");
		WebElement element = driver.findElement(By.name("item.stock"));
		element.sendKeys("99");
	    element.submit();
	    
	    try {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@data-record-key='4']")));
	    } catch (Exception e) {
	    	fail("Item criado não foi encontrada."); 
	    }

	    assertEquals(1, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM ITEM WHERE NR_PRECO_DE = ?", new Object[]{"100.50"}));
	    
		List<WebElement> elements = driver.findElements(By.xpath("//tr[@data-record-key='4']/td"));
		assertEquals("Subcategoria T", elements.get(0).getText());
	}
	
	@Test
	public void update() {
		driver.findElements(By.className("jtable-edit-command-button")).get(1).click();

		wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("subcategory.name"))));
		WebElement element = driver.findElement(By.name("subcategory.name"));
	    element.sendKeys("A");
	    element.submit();
	    
	    try{
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@data-record-key='1']")));
	    } catch (Exception e) {
	    	fail("Subcategoria editada não foi encontrada."); 
	    }
	    
	    assertEquals(1, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM SUBCATEGORIA WHERE NM_NOME = ?", new Object[]{"Subcategoria 1A"}));
	    
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("jtable-delete-command-button")));
	    List<WebElement> elements = driver.findElements(By.xpath("//tr[@data-record-key='1']/td"));
	    assertEquals("Subcategoria 1A", elements.get(4).getText());
	}
	
//	@Test
	public void delete() {
		driver.findElements(By.className("jtable-delete-command-button")).get(1).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='DeleteDialogButton']")));
		driver.findElement(By.xpath("//button[@id='DeleteDialogButton']")).click();
		
		try{
	    	wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//tr[@data-record-key='1']"), "Subcategoria 1"));
	    } catch (Exception e) {
	    	fail("Elemento não deveria ter sido encontrado");
	    }
		
		try {
			driver.findElement(By.xpath("//tr[@data-record-key='1']"));
			fail("Elemento não deveria ter sido encontrado");
		} catch (Exception e) {
			
		}
		
		assertEquals(0, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM SUBCATEGORIA WHERE NM_NOME = ?", new Object[]{"Subcategoria 1"}));
	}
	
}