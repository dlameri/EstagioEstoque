package com.ideais.stock.webservice;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ideais.stock.dao.ItemDao;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;

@Path("/item")
public class ItemWS {
	ItemDao itemDao = new ItemDao();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Item> getItems(@QueryParam("orderColumn") @DefaultValue("rank") String orderColumn, @QueryParam("order") @DefaultValue("desc") String order, @QueryParam("active") @DefaultValue("true") String active, @QueryParam("firstResult") @DefaultValue("0") String firstResult, @QueryParam("maxResults") @DefaultValue("20") String maxResults) {
		return itemDao.personalizedQuery(orderColumn, order, active, firstResult, maxResults);
	}
	
	@Path("/orderbyrank")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Item> getItemsOrderByRank() {
		return itemDao.findAllOrderByRank();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Item getItemById(@PathParam("id") Long id) {
		return itemDao.findById(id);
	}
	
	@Path("/byproductid/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Item> searchItemsByCategoryId(@PathParam("id") Long id, @QueryParam("orderColumn") @DefaultValue("rank") String orderColumn, @QueryParam("order") @DefaultValue("desc") String order, @QueryParam("active") @DefaultValue("true") String active, @QueryParam("firstResult") @DefaultValue("0") String firstResult, @QueryParam("maxResults") @DefaultValue("20") String maxResults) {
		Product product = new Product();
		product.setId(id);
		return itemDao.findByProductId(product, orderColumn, order, active, firstResult, maxResults);
	}
	
}
