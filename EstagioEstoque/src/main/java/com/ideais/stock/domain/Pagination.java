package com.ideais.stock.domain;

public class Pagination {

	private String orderColumn = "id";
	private String order = "asc";
	private String firstResult = "0";
	private String maxResults = "10";

	public Pagination(String orderColumn, String order, String firstResult,
			String maxResults) {
		this.orderColumn = orderColumn;
		this.order = order;
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}
	
	public Pagination(String firstResult, String maxResults) {
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}
	
	public Pagination() {}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}

	public String getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(String maxResults) {
		this.maxResults = maxResults;
	}
	
}
