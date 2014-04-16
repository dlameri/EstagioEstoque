package com.ideais.stock.json;

import java.util.ArrayList;
import java.util.List;

import com.ideais.stock.domain.Subcategory;

public class SubcategoryJSON {

	private Long id;
	private String name;
	private Boolean active;
	private List<Link> productsJSON = new ArrayList<Link>();
	
	public SubcategoryJSON(Subcategory subcategory) {
		this.id = subcategory.getId();
		this.name = subcategory.getName();
		this.active = subcategory.getActive();
		productsJSON.add(new Link("subcategory/"+ id +"/product", "product"));
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

	public List<Link> getProductsJSON() {
		return productsJSON;
	}

	public void setProductsJSON(List<Link> productsJSON) {
		this.productsJSON = productsJSON;
	}
}
