package com.ideais.stock.system;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryActionTest extends AbstractSystemTest {

	private WebDriverWait wait;
	private WebElement element;
	
	@Before
	public void setUp() {
		wait = new WebDriverWait(driver, 10);
		driver.get("http://localhost:8080/EstagioEstoque/web/");
		driver.findElement(By.name("email")).submit();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='categorias']")));
		driver.findElement(By.xpath("//a[@href='categorias']")).click();
	}
	
	@Test
	public void create_category_test() {
	    element = driver.findElement(By.name("category.name"));
	    element.sendKeys("Categoria S");
	    element.submit();
	    
	    try {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name-4")));
	    } catch (Exception e) {
	    	fail("Categoria criada não foi encontrada."); 
	    }
	    
		assertEquals("Categoria S", getTextById("name-4"));
	    assertEquals(1, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM CATEGORIA WHERE NM_NOME = ?", new Object[]{"Categoria S"}));
	}
	
	@Test
	public void edit_category_test() {
		driver.findElement(By.id("edit-1")).click();
	    element = driver.findElement(By.name("category.name"));
	    element.sendKeys("A");
	    element.submit();
	    
	    try{
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name-1")));
	    } catch (Exception e) {
	    	fail("Categoria editada não foi encontrada."); 
	    }
	    
	    assertEquals("Categoria 1A", getTextById("name-1"));
	}
	
	@Test
	public void delete_category_without_children_test() {
		driver.findElement(By.id("delete-3")).click();
		
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("name-3")));
		} catch (Exception e) {
			fail("Elemento não deveria ter sido encontrado");
		}
		
		try {
			driver.findElement(By.id("name-3"));
			fail("Elemento não deveria ter sido encontrado");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void delete_category_with_children_test() {
		driver.findElement(By.id("delete-1")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='deletecategory?id=1&confirmation=ok']")));
		driver.findElement(By.xpath("//button[@id='deletecategory?id=1&confirmation=ok']")).click();
		
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("name-1")));
		} catch (Exception e) {
			fail("Elemento não deveria ter sido encontrado");
		}
		
		try {
			driver.findElement(By.id("name-1"));
			fail("Elemento não deveria ter sido encontrado");
		} catch (Exception e) {
			
		}
	}
	
}