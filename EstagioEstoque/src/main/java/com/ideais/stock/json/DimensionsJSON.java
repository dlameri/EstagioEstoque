package com.ideais.stock.json;

import com.ideais.stock.domain.Dimensions;

public class DimensionsJSON {

	private Long id;
	private Double height;
	private Double width;
	private Double depth;
	
	public DimensionsJSON(Dimensions dimensions) {
		this.id = dimensions.getId();
		this.height = dimensions.getHeight();
		this.width = dimensions.getWidth();
		this.depth = dimensions.getDepth();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}
}
