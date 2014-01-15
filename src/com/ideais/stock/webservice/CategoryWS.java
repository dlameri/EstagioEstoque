package com.ideais.stock.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.*;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.domain.Category;

@Path("/api/category")
public class CategoryWS {
	CategoryDao categoryDao = new CategoryDao();

	@Path("/all")
	@GET
	@Produces("application/json")
	public List<Category> getCategories() {
		return categoryDao.findAll();
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Category getCategoryById(@PathParam("id") Long id) {
		return categoryDao.findById(id);
	}
}
