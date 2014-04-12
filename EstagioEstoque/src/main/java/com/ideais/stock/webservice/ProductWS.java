package com.ideais.stock.webservice;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.ProductService;

@Path("/product")
public class ProductWS {
	
	@Autowired
	ProductService productService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> getProducts(@QueryParam("orderColum") @DefaultValue("rank") String orderColum, @QueryParam("order") @DefaultValue("desc") String order, @QueryParam("active") @DefaultValue("true") String active, @QueryParam("firstResult") @DefaultValue("0") String firstResult, @QueryParam("maxResults") @DefaultValue("20") String maxResults) {
		return productService.personalizedQuery(orderColum, order, active, firstResult, maxResults);
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Product getProductById(@PathParam("id") Long id) {
		return productService.findById(id);
	}

	@Path("/search/{textToSearch}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> searchProducts(@PathParam("textToSearch") String textToSearch) {
		return productService.search(textToSearch);
	}
	
	@Path("/bycategoryid/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> searchProductsByCategyId(@PathParam("id") Long id) {
		Category category = new Category();
	    	category.setId(id);
		return productService.findByCategoryId(category);
	}
	
	@Path("/bysubcategoryid/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> searchProductsBySubcategoryId(@PathParam("id") Long id) {
	    	Subcategory subcategory = new Subcategory();
	    	subcategory.setId(id);
		return productService.findBySubcategoryId(subcategory);
	}
	
	@Path("/orderbyrank")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> getProductsOrderByRank() {
		return productService.findAllOrderByRank();
	}

}
