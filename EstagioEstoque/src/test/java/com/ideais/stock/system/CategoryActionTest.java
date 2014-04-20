package com.ideais.stock.system;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
		WebElement element = driver().findElement(By.name("email"));
		element.submit();
		element = driver().findElement(By.linkText("Categoria"));
		element.submit();
	}
	
	@Test
	public void create_category_test() {
	    WebElement element = driver().findElement(By.name("category.name"));
	    element.sendKeys("Livros");
	    element.submit();
	    System.out.println("Page title is: " + driver().getTitle());
	    assertEquals("Livros", categoryService.findById(1L).getName());
	}

	@After
	public void destroy() {
	    driver().quit();
	}
}
