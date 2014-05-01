package com.ideais.stock.json.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import com.ideais.stock.domain.Product;
import com.ideais.stock.json.ItemJSON;

public class InternalProductJSON implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private Integer weight;
	private Integer warranty;
	private String brand;
	private String model;
	private Boolean active;
	private Boolean promo;
	private Integer rank;
	private Integer count;
	
	@Transient
	private List<ItemJSON> items = new ArrayList<ItemJSON>();
	private Long categoryId;
	private Long subcategoryId;
	private Double width;
	private Double height;
	private Double depth;

	public InternalProductJSON(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.shortDescription = product.getShortDescription();
		this.longDescription = product.getLongDescription();
		this.weight = product.getWeight();
		this.warranty = product.getWarranty();
		this.brand = product.getBrand();
		this.model = product.getModel();
		this.active = product.getActive();
		this.promo = product.getPromo();
		this.rank = product.getRank();
		this.count = product.getCount();
		this.categoryId = product.getCategory().getId();
		this.subcategoryId = product.getSubcategory().getId();
		this.width = product.getDimensions().getWidth();
		this.height = product.getDimensions().getHeight();
		this.depth = product.getDimensions().getDepth();
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<ItemJSON> getItems() {
		return items;
	}

	public void setItems(List<ItemJSON> items) {
		this.items = items;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}

	public Boolean getPromo() {
		return promo;
	}

	public void setPromo(Boolean promo) {
		this.promo = promo;
	}
	
}