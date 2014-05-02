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

import com.ideais.stock.domain.Pagination;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.ProductJSON;
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.service.ProductService;
import com.ideais.stock.service.SubcategoryService;

@Path("/subcategory")
public class SubcategoryWS {
	
	@Autowired
	SubcategoryService subcategoryService;
	@Autowired
	ProductService productService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<SubcategoryJSON> getSubcategories() {
		List<SubcategoryJSON> subcategoryJSONs = new ArrayList<SubcategoryJSON>();
		
		for (Subcategory subcategory : subcategoryService.findAll(true)) {
			subcategoryJSONs.add(new SubcategoryJSON(subcategory));
		}
		
		return subcategoryJSONs;
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public SubcategoryJSON getSubcategoryById(@PathParam("id") Long id) {
		return new SubcategoryJSON(subcategoryService.findById(id));
	}
	
	@Path("{id}/product")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductJSON> getProductsBySubcategoryId(@PathParam("id") Long id,
			@QueryParam("orderColumn") @DefaultValue("rank") String orderColumn,
			@QueryParam("order") @DefaultValue("desc") String order,
			@QueryParam("active") @DefaultValue("true") Boolean active,
			@QueryParam("firstResult") @DefaultValue("0") String firstResult,
			@QueryParam("maxResults") @DefaultValue("20") String maxResults,
			@QueryParam("hasItems") @DefaultValue("true") Boolean hasItems) {
		Subcategory subcategory = new Subcategory();
		subcategory.setId(id);
		
		Pagination pagination = new Pagination(orderColumn, order, firstResult, maxResults);
		
		List<Product> products = productService.findProductsBySubcategoryId(active, pagination, hasItems, subcategory);
		List<ProductJSON> productJSONs = new ArrayList<ProductJSON>();
		
		for (Product product : products) {
			productJSONs.add(new ProductJSON(product));
		}
		
		return productJSONs;
	}
}
