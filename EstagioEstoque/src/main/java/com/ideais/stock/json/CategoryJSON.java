package com.ideais.stock.json;

import java.util.ArrayList;
import java.util.List;

import com.ideais.stock.domain.Category;

public class CategoryJSON {

	private Long id;
	private String name;
	private Boolean active;
	private List<Link> subcategories = new ArrayList<Link>();
	
	public CategoryJSON(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.active = category.getActive();
		subcategories.add(new Link("category/"+this.id+"/subcategory","subcategory"));
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

	public List<Link> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Link> subcategories) {
		this.subcategories = subcategories;
	}
	
}
