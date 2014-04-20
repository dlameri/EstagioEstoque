package com.ideais.stock.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.json.DimensionsJSON;
import com.ideais.stock.json.ItemJSON;
import com.ideais.stock.json.ProductJSON;
import com.ideais.stock.service.ProductService;

@Path("/product")
public class ProductWS {

	@Autowired
	ProductService productService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductJSON> getProducts(
			@QueryParam("orderColumn") @DefaultValue("rank") String orderColumn,
			@QueryParam("order") @DefaultValue("desc") String order,
			@QueryParam("active") @DefaultValue("true") String active,
			@QueryParam("firstResult") @DefaultValue("0") String firstResult,
			@QueryParam("maxResults") @DefaultValue("20") String maxResults) {
		List<ProductJSON> productJSONs = new ArrayList<ProductJSON>();

		for (Product product : productService.personalizedQuery(orderColumn,
				order, active, firstResult, maxResults)) {
			productJSONs.add(new ProductJSON(product));
		}

		return productJSONs;
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ProductJSON getProductById(@PathParam("id") Long id) {
		return new ProductJSON(productService.findById(id));
	}

	@Path("/search/{textToSearch}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductJSON> searchProducts(
			@PathParam("textToSearch") String textToSearch) {
		List<ProductJSON> productJSONs = new ArrayList<ProductJSON>();

		for (Product product : productService.search(textToSearch)) {
			productJSONs.add(new ProductJSON(product));
		}

		return productJSONs;
	}
	
	@Path("/{id}/item")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ItemJSON> getItemsByProductId(@PathParam("id") Long id) {
		List<ItemJSON> itemJSONs = new ArrayList<ItemJSON>();
		
		for (Item item : productService.findById(id).getItems()) {
			itemJSONs.add(new ItemJSON(item));
		}

		return itemJSONs;
	}

	@Path("/{id}/dimensions")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public DimensionsJSON getDimensionsByProductId(@PathParam("id") Long id) {
		return new DimensionsJSON(productService.findById(id).getDimensions());
	}
}
