package com.ideais.stock.json;

import java.util.List;

import com.ideais.stock.domain.Product;

public class ProductJSON {
	
	private Long id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private Integer weight;
	private Integer warranty;
	private String brand;
	private String model;
	private Boolean active;
	private Integer rank;
	private CategoryJSON categoryJSON;
	private SubcategoryJSON subcategoryJSON;
	private List<ItemJSON> itemJSON;
	
	public ProductJSON(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.shortDescription = product.getShortDescription();
		this.longDescription = product.getLongDescription();
		this.weight = product.getWeight();
		this.warranty = product.getWarranty();
		this.brand = product.getBrand();
		this.model = product.getModel();
		this.active = product.getActive();
		this.rank = product.getRank();
		this.categoryJSON = product.getCategory().toJSON();
		this.subcategoryJSON = product.getSubcategory().toJSON();
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public CategoryJSON getCategoryJSON() {
		return categoryJSON;
	}

	public void setCategoryJSON(CategoryJSON categoryJSON) {
		this.categoryJSON = categoryJSON;
	}

	public SubcategoryJSON getSubcategoryJSON() {
		return subcategoryJSON;
	}

	public void setSubcategoryJSON(SubcategoryJSON subcategoryJSON) {
		this.subcategoryJSON = subcategoryJSON;
	}

	public List<ItemJSON> getItemJSON() {
		return itemJSON;
	}

	public void setItemJSON(List<ItemJSON> itemJSON) {
		this.itemJSON = itemJSON;
	}

}
