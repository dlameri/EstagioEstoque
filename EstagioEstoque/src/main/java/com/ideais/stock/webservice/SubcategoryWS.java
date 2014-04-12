package com.ideais.stock.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.SubcategoryService;

@Path("/subcategory")
public class SubcategoryWS {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	SubcategoryService subcategoryService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Subcategory> getSubategories() {
		return subcategoryService.findAll();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Subcategory getSubcategoriesById(@PathParam("id") Long id) {
		return subcategoryService.findById(id);
	}
	
	@Path("/category/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Subcategory> getSubcategoriesByCategoryId(@PathParam("id") Long id) {
		Category category = categoryService.findById(id);
		return subcategoryService.findByCategoryId(category);
	}
}
