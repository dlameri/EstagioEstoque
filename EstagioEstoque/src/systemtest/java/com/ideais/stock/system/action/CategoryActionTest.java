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
public class CategoryActionTest extends AbstractSystemTest implements SystemTestBehavior {

	@Before
	public void setUp() {
		driver.get("http://localhost:8080/EstagioEstoque/web/");
		driver.findElement(By.name("email")).submit();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='categorias']")));
		driver.findElement(By.xpath("//a[@href='categorias']")).click();
		
		wait.until((ExpectedConditions.visibilityOfElementLocated(By.className("jtable-toolbar-item-add-record"))));
		wait.until((ExpectedConditions.visibilityOfElementLocated(By.className("jtable-edit-command-button"))));
		wait.until((ExpectedConditions.visibilityOfElementLocated(By.className("jtable-delete-command-button"))));
	}
	
	@Test
	public void create() {
		driver.findElement(By.className("jtable-toolbar-item-add-record")).click();
		
		wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("category.name"))));
		WebElement element = driver.findElement(By.name("category.name"));
	    element.sendKeys("Categoria S");
	    element.submit();
	    
	    try {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@data-record-key='4']")));
	    } catch (Exception e) {
	    	fail("Categoria criada não foi encontrada."); 
	    }

	    assertEquals(1, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM CATEGORIA WHERE NM_NOME = ?", new Object[]{"Categoria S"}));
	    
	    List<WebElement> elements = driver.findElements(By.xpath("//tr[@data-record-key='4']/td"));
		assertEquals("Categoria S", elements.get(1).getText());
	}
	
	@Test
	public void update() {
		driver.findElement(By.className("jtable-edit-command-button")).click();

		wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("category.name"))));
		WebElement element = driver.findElement(By.name("category.name"));
	    element.sendKeys("A");
	    element.submit();
	    
	    try{
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@data-record-key='1']")));
	    } catch (Exception e) {
	    	fail("Categoria editada não foi encontrada."); 
	    }
	    
	    assertEquals(1, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM CATEGORIA WHERE NM_NOME = ?", new Object[]{"Categoria 1A"}));
	    
	    List<WebElement> elements = driver.findElements(By.xpath("//tr[@data-record-key='1']/td"));
	    assertEquals("Categoria 1A", elements.get(1).getText());
	}
	
	@Test
	public void delete() {
		driver.findElement(By.className("jtable-delete-command-button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DeleteDialogButton")));
		driver.findElement(By.id("DeleteDialogButton")).click();
		
		try{
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tr[@data-record-key='1']")));
	    } catch (Exception e) {
	    	fail("Elemento não deveria ter sido encontrado");
	    }
		
		try {
			driver.findElement(By.xpath("//tr[@data-record-key='1']"));
			fail("Elemento não deveria ter sido encontrado");
		} catch (Exception e) {
			
		}
		
		assertEquals(0, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM CATEGORIA WHERE NM_NOME = ? AND BO_ATIVO = ?", new Object[]{"Categoria 1","1"}));
	}
	
}