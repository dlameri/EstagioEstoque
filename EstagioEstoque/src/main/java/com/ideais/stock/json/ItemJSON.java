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
	private String formattedPriceFrom;
	private String formattedPriceFor;
	private List<Link> links = new ArrayList<Link>();
	private Integer count;
	
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
		this.count = item.getCount();
		this.formattedPriceFrom = item.getFormatedPriceFrom();
		this.formattedPriceFor = item.getFormatedPriceFor();
		
		links.add(new Link("item/"+id+"/image", "image"));
		links.add(new Link("product/"+item.getProductId(),"product"));
	}
	
	public String getURI(String name) {
		
		if(name != null) {
			for (Link link : links) {
				if (name.equals(link.getName())) {
					return link.getHref();
				}
			}
		}
		
		return null; 
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

	public String getFormattedPriceFrom() {
		return formattedPriceFrom;
	}

	public void setFormattedPriceFrom(String formattedPriceFrom) {
		this.formattedPriceFrom = formattedPriceFrom;
	}

	public String getFormattedPriceFor() {
		return formattedPriceFor;
	}

	public void setFormattedPriceFor(String formattedPriceFor) {
		this.formattedPriceFor = formattedPriceFor;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}
