package com.ideais.stock.json.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.ProductJSON;

public class SubcategoryInternalJSON implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Boolean active;
	private Long categoryId;
	private Integer count;
	
	private List<ProductJSON> products = new ArrayList<ProductJSON>();

	public SubcategoryInternalJSON(Subcategory subcategory) {
		this.id = subcategory.getId();
		this.name = subcategory.getName();
		this.active = subcategory.getActive();
		this.categoryId = subcategory.getCategory().getId();
		this.count = subcategory.getCount();
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<ProductJSON> getProducts() {
		return products;
	}

	public void setProducts(List<ProductJSON> products) {
		this.products = products;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
