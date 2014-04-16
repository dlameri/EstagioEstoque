package com.ideais.stock.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ideais.stock.domain.Item;

public class ItemJSON {
	
	private Long id;
	private Long sku;
	private BigDecimal priceFrom;
	private BigDecimal priceFor;
	private String optionName;
	private String optionValue;
	private Integer stock;
	private Integer rank;
	private Boolean active;
	private List<Link> imageJSON = new ArrayList<Link>();
	
	public ItemJSON(Item item) {
		this.id = item.getId();
		this.sku = item.getSku();
		this.priceFrom = item.getPriceFrom();
		this.priceFor = item.getPriceFor();
		this.optionName = item.getOptionName();
		this.optionValue = item.getOptionValue();
		this.stock = item.getStock();
		this.rank = item.getRank();
		this.active = item.getActive();
		imageJSON.add(new Link("item/"+id+"/image", "image"));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	public BigDecimal getPriceFor() {
		return priceFor;
	}

	public void setPriceFor(BigDecimal priceFor) {
		this.priceFor = priceFor;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Link> getImageJSON() {
		return imageJSON;
	}

	public void setImageJSON(List<Link> imageJSON) {
		this.imageJSON = imageJSON;
	}
	
}
