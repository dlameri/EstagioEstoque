package com.ideais.stock.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.dao.SubcategoryDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;

@Path("/subcategory")
public class SubcategoryWS {
	
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	SubcategoryDao subcategoryDao;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Subcategory> getSubategories() {
		return subcategoryDao.findAll();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Subcategory getSubcategoriesById(@PathParam("id") Long id) {
		return subcategoryDao.findById(id);
	}
	
	@Path("/category/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Subcategory> getSubcategoriesByCategoryId(@PathParam("id") Long id) {
		Category category = categoryDao.findById(id);
		return subcategoryDao.findByCategoryId(category);
	}
}
