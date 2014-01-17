package com.ideais.stock.domain;

public class Dimensions {

	private Double height;
	
	private Double width;
	
	private Double depth;

	public Dimensions(Double height, Double width, Double depth) {
		this.height = height;
		this.width = width;
		this.depth = depth;
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
