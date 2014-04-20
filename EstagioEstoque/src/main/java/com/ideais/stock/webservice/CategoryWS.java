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

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.CategoryJSON;
import com.ideais.stock.json.ProductJSON;
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.ProductService;

@Path("/category")
public class CategoryWS {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<CategoryJSON> getCategories() {
		List<CategoryJSON> categoryJSONs = new ArrayList<CategoryJSON>();
		
		for (Category category : categoryService.findAll()) {
			categoryJSONs.add(new CategoryJSON(category));
		}
		return categoryJSONs;
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public CategoryJSON getCategoryById(@PathParam("id") Long id) {
		return new CategoryJSON(categoryService.findById(id));
	}
	
	@Path("/{id}/subcategory")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<SubcategoryJSON> getSubcategoriesByCategoryId(@PathParam("id") Long id) {
		Category category = categoryService.findById(id);
		
		List<SubcategoryJSON> subcategoryJSONs = new ArrayList<SubcategoryJSON>();
		for (Subcategory  subcategory : category.getSubcategories()) {
			subcategoryJSONs.add( new SubcategoryJSON(subcategory));
		}
		
		return subcategoryJSONs;
	}
	
	@Path("/{id}/product")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductJSON> getProductsByCategoryId(@PathParam("id") Long id,
			@QueryParam("orderColumn") @DefaultValue("rank") String orderColumn,
			@QueryParam("order") @DefaultValue("desc") String order,
			@QueryParam("active") @DefaultValue("true") String active,
			@QueryParam("firstResult") @DefaultValue("0") String firstResult,
			@QueryParam("maxResults") @DefaultValue("10") String maxResults) {
		Category category = new Category();
		category.setId(id);
		
		List<ProductJSON> productJSONs = new ArrayList<ProductJSON>();

		for (Product product : productService.findByCategoryId(category, orderColumn, order, active, firstResult, maxResults)) {
			productJSONs.add(new ProductJSON(product));
		}

		return productJSONs;
	}
	
	@Path("/{id}/topproducts")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductJSON> getTopProductsByCategory(@PathParam("id") Long id,
			@QueryParam("orderColumn") @DefaultValue("rank") String orderColumn,
			@QueryParam("order") @DefaultValue("desc") String order,
			@QueryParam("active") @DefaultValue("true") String active,
			@QueryParam("firstResult") @DefaultValue("0") String firstResult,
			@QueryParam("maxResults") @DefaultValue("10") String maxResults) {
		Category category = new Category();
		category.setId(id);
		
		List<ProductJSON> productJSONs = new ArrayList<ProductJSON>();

		for (Product product : productService.findByCategoryId(category, orderColumn, order, active, firstResult, maxResults)) {
			productJSONs.add(new ProductJSON(product));
		}

		return productJSONs;
	}
}
