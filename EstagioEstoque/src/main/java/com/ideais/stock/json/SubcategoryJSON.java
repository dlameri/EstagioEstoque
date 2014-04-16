package com.ideais.stock.json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonBackReference;

import com.ideais.stock.domain.Subcategory;

public class SubcategoryJSON {

	private Long id;
	private String name;
	private Boolean active;
	@JsonBackReference
	private CategoryJSON categoryJSON;
	private List<ProductJSON> productsJSON;
	
	public SubcategoryJSON(Subcategory subcategory) {
		this.id = subcategory.getId();
		this.name = subcategory.getName();
		this.active = subcategory.getActive();
		this.categoryJSON = subcategory.getCategory().toJSON();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public CategoryJSON getCategoryJSON() {
		return categoryJSON;
	}

	public void setCategoryJSON(CategoryJSON categoryJSON) {
		this.categoryJSON = categoryJSON;
	}

	public List<ProductJSON> getProductsJSON() {
		return productsJSON;
	}

	public void setProductsJSON(List<ProductJSON> productsJSON) {
		this.productsJSON = productsJSON;
	}
}
