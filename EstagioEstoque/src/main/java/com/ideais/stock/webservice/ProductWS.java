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

import com.ideais.stock.dao.ProductDao;
import com.ideais.stock.domain.CartWS;
import com.ideais.stock.domain.Product;

@Path("/product")
public class ProductWS {
	ProductDao productDao = new ProductDao();

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

	@Path("/updatestock")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response consumeJSON(CartWS cart) {
		String output = cart.toString();
		return Response.status(200).entity(output).build();
	}

}
