package com.ideais.stock.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.dao.ProductDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Product;
import com.ideais.stock.webservice.domain.CartWS;

@Path("/product")
public class ProductWS {
	ProductDao productDao = new ProductDao();
	CategoryDao categoryDao = new CategoryDao();
	Category category = new Category();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> getProducts() {
		return productDao.findAll();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Product getProductById(@PathParam("id") Long id) {
		return productDao.findById(id);
	}

	@Path("/seach/{textToSeach}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> seachProducts(@PathParam("textToSeach") String textToSeach) {
		return productDao.seach(textToSeach);
	}
	
	@Path("/bycategoryid/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> seachProductsByCategyId(@PathParam("id") Long id) {
	    	category.setId(id);
		return productDao.findByCategoryId(category);
	}

	@Path("/updatestock")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response consumeJSON(CartWS cart) {
		String output = cart.toString();
		return Response.status(200).entity(output).build();
	}

}
