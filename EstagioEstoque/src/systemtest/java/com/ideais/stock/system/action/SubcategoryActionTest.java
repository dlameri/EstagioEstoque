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
public class SubcategoryActionTest extends AbstractSystemTest implements SystemTestBehavior {

	
	@Before
	public void setUp() {
		driver.get("http://localhost:8080/EstagioEstoque/web/");
		driver.findElement(By.name("email")).submit();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='categorias']")));
		driver.findElement(By.xpath("//a[@href='categorias']")).click();
		
		//Abrir jTable de Subcategoria
		wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@data-record-key='1']/td/img"))));
		driver.findElement(By.xpath("//tr[@data-record-key='1']/td/img")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("jtable-delete-command-button")));
	}
	
	@Test
	public void create() {
		driver.findElements(By.className("jtable-toolbar-item-add-record")).get(1).click();
		
		wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("subcategory.name"))));
		WebElement element = driver.findElement(By.name("subcategory.name"));
	    element.sendKeys("Subcategoria T");
	    element.submit();
	    
	    try {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@data-record-key='4']")));
	    } catch (Exception e) {
	    	fail("Subcategoria criada não foi encontrada."); 
	    }

	    assertEquals(1, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM SUBCATEGORIA WHERE NM_NOME = ?", new Object[]{"Subcategoria T"}));
	    
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
		
		assertEquals(0, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM SUBCATEGORIA WHERE NM_NOME = ? AND BO_ATIVO = ?", new Object[]{"Subcategoria 1","1"}));		
	}
	
}