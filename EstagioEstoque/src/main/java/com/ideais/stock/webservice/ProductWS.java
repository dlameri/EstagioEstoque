package com.ideais.stock.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ideais.stock.dao.ItemDao;
import com.ideais.stock.domain.Item;

@Path("/product")
public class ProductWS {
	ItemDao productDao = new ItemDao();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Item> getProducts() {
		return productDao.findAll();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Item getProductById(@PathParam ("id") Long id) {
		return productDao.findById(id);
	}
//	
//	@Path("/category/{id}")
//	@GET
//	@Produces({ MediaType.APPLICATION_JSON })
//	public List<Subcategory> getSubategoriesByCategoryId(@PathParam("id") Long id) {
//		Category category = categoryDao.findById(id);
//		return subcategoryDao.findByCategoryId(category);
//	}

}
