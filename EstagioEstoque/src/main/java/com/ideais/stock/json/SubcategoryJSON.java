package com.ideais.stock.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ideais.stock.domain.Subcategory;

public class SubcategoryJSON implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Boolean active;
	private List<Link> links = new ArrayList<Link>();

	public SubcategoryJSON(Subcategory subcategory) {
		this.id = subcategory.getId();
		this.name = subcategory.getName();
		this.active = subcategory.getActive();
		links.add(new Link("subcategory/" + id + "/product", "product"));
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
