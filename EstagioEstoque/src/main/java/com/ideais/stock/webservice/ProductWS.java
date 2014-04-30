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
import com.ideais.stock.domain.Pagination;
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
			@QueryParam("active") @DefaultValue("true") Boolean active,
			@QueryParam("firstResult") @DefaultValue("0") String firstResult,
			@QueryParam("maxResults") @DefaultValue("20") String maxResults,
			@QueryParam("hasItems") @DefaultValue("true") Boolean hasItems,
			@QueryParam("promo") @DefaultValue("false") Boolean promo) {
		List<ProductJSON> productJSONs = new ArrayList<ProductJSON>();
		Pagination pagination = new Pagination(orderColumn, order, firstResult, maxResults);
		
		if (promo) {
			for (Product product : productService.findPromoProducts()) {
				productJSONs.add(new ProductJSON(product));
			}

			return productJSONs;
		}
		
		for (Product product : productService.findAllWithPagination(active, pagination, hasItems)) {
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
			@QueryParam("orderColumn") @DefaultValue("name") String orderColumn,
			@QueryParam("order") @DefaultValue("asc") String order,
			@QueryParam("active") @DefaultValue("true") Boolean active,
			@QueryParam("firstResult") @DefaultValue("0") String firstResult,
			@QueryParam("maxResults") @DefaultValue("20") String maxResults,
			@PathParam("textToSearch") String textToSearch) {
		List<ProductJSON> productJSONs = new ArrayList<ProductJSON>();
		Pagination pagination = new Pagination(orderColumn, order, firstResult, maxResults);
		
		for (Product product : productService.search(active, pagination, textToSearch)) {
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
