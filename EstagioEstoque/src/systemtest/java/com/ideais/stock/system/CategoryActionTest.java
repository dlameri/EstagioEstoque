package com.ideais.stock.system;

import static junit.framework.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideais.stock.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryActionTest extends AbstractSystemTest {

	@Autowired
	private CategoryService categoryService;
	private WebDriverWait wait;
	private WebElement element;
	
	@Before
	public void setUp() {
		wait = new WebDriverWait(driver(), 10);
		driver().get("http://localhost:8080/EstagioEstoque/web/");
		driver().findElement(By.name("email")).submit();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='categorias']")));
		driver().findElement(By.xpath("//a[@href='categorias']")).click();
	}
	
	@Test
	public void create_category_test() {
	    element = driver().findElement(By.name("category.name"));
	    element.sendKeys("Categoria-C");
	    element.submit();
	    assertEquals("Categoria-C", driver().findElement(By.cssSelector(".Categoria-C")).getText().split(" ")[0]);
	}
	
	@Test
	public void edit_category_test() {
		driver().findElement(By.cssSelector(".Categoria-C>a")).click();
	    element = driver().findElement(By.name("category.name"));
	    element.sendKeys("A");
	    element.submit();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Categoria-CA")));
	    assertEquals("Categoria-CA", driver().findElement(By.cssSelector(".Categoria-CA")).getText().split(" ")[0]);
	}
	
	@Test
	public void delete_category_test() {
		driver().findElement(By.cssSelector(".Categoria-CA>button")).click();
	    assertEquals(0, driver().findElement(By.cssSelector("h3")).getSize());
	}

	@After
	public void destroy() {
	    driver().quit();
	}
}