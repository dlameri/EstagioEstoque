package com.ideais.stock.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ideais.stock.dao.ItemDao;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;

@Path("/item")
public class ItemWS {
	ItemDao itemDao = new ItemDao();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Item> getProducts() {
		return itemDao.findAll();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Item getProductById(@PathParam("id") Long id) {
		return itemDao.findById(id);
	}
	
	@Path("/byproductid/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Item> seachProductsByCategyId(@PathParam("id") Long id) {
		Product product = new Product();
		product.setId(id);
		return itemDao.findByProductId(product);
	}
	
	 @Path("/itemsbyids")
	 @POST
	 @Consumes({ MediaType.APPLICATION_JSON })
	 @Produces({ MediaType.APPLICATION_JSON })
	 public List<Item> consumeJSON(List<Long> ids) {	
	 return itemDao.findByIds(ids);
	 }
}
