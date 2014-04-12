package com.ideais.stock.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.service.CategoryService;

@Path("/category")
public class CategoryWS {
	
	@Autowired
	private CategoryService categoryService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Category> getCategories() {
		return categoryService.findAll();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Category getCategoryById(@PathParam("id") Long id) {
		return categoryService.findById(id);
	}
	
}
