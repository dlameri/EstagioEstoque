package com.ideais.stock.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.ProductJSON;
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.service.SubcategoryService;

@Path("/subcategory")
public class SubcategoryWS {
	
	@Autowired
	SubcategoryService subcategoryService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<SubcategoryJSON> getSubcategories() {
		List<SubcategoryJSON> subcategoryJSONs = new ArrayList<SubcategoryJSON>();
		
		for (Subcategory subcategory : subcategoryService.findAll()) {
			subcategoryJSONs.add(new SubcategoryJSON(subcategory));
		}
		
		return subcategoryJSONs;
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public SubcategoryJSON getSubcategoriesById(@PathParam("id") Long id) {
		return new SubcategoryJSON(subcategoryService.findById(id));
	}
	
	@Path("{id}/product")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductJSON> getProductBySubcategory(@PathParam("id") Long id) {
		Subcategory subcategory = subcategoryService.findById(id);
		
		List<ProductJSON> productJSONs = new ArrayList<ProductJSON>();
		for (Product product : subcategory.getProducts()) {
			productJSONs.add(new ProductJSON(product));
		}
		
		return productJSONs;
	}
}
