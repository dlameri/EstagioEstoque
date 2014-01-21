package main.java.com.ideais.stock.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.com.ideais.stock.dao.CategoryDao;
import main.java.com.ideais.stock.dao.SubcategoryDao;
import main.java.com.ideais.stock.domain.Category;
import main.java.com.ideais.stock.domain.Subcategory;

@Path("/subcategory")
public class SubcategoryWS {
	SubcategoryDao subcategoryDao = new SubcategoryDao();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Subcategory> getSubategories() {
		return subcategoryDao.findAll();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Subcategory getSubategoriesById(@PathParam("id") Long id) {
		return subcategoryDao.findById(id);
	}
	
	@Path("/category/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Subcategory> getSubategoriesByCategoryId(@PathParam("id") Long id) {
		return subcategoryDao.findByCategoryId(id);
	}
}
