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
	
	@Before
	public void setUp() {
		driver().get("http://localhost:8080/EstagioEstoque/web/");
		driver().findElement(By.name("email")).submit();
		WebDriverWait wait = new WebDriverWait(driver(), 10); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='categorias']")));
		driver().findElement(By.xpath("//a[@href='categorias']")).click();
	}
	
	@Test
	public void create_category_test() {
	    WebElement element = driver().findElement(By.name("category.name"));
	    element.sendKeys("Categoria-S");
	    element.submit();
	    assertEquals("Categoria-S", driver().findElement(By.cssSelector(".Categoria-S")).getText().split(" ")[0]);
	}

	@After
	public void destroy() {
	    driver().quit();
	}
}