package com.ideais.stock.json;

import java.util.ArrayList;
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
	private Boolean promo;
	private Integer rank;
	private List<Link> links = new ArrayList<Link>();
	private Integer count;
	
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
		this.promo = product.getPromo();
		this.rank = product.getRank();
		this.count = product.getCount();
		links.add(new Link("product/" + id + "/item", "item"));
		links.add(new Link("product/" + id + "/dimensions", "dimensions"));
	}

	public String getURI(String name) {

		if (name != null) {
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

	public Boolean getPromo() {
		return promo;
	}

	public void setPromo(Boolean promo) {
		this.promo = promo;
	}

}
