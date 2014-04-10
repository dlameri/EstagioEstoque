package com.ideais.stock.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.domain.Category;

@Path("/category")
public class CategoryWS {
	
	@Autowired
	private CategoryDao categoryDao;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Category> getCategories() {
		return categoryDao.findAll();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Category getCategoryById(@PathParam("id") Long id) {
		return categoryDao.findById(id);
	}
	
}
