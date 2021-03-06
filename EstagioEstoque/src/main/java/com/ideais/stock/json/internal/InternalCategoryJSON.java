package com.ideais.stock.json.internal;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.ideais.stock.domain.Category;

public class InternalCategoryJSON implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Boolean active;
	private Integer count;

	public InternalCategoryJSON(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.active = category.getActive();
		this.count = category.getCount();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("SeraUmNome")
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}