package com.ideais.stock.json.internal;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

public class ResponseJSON<T> {

	private String result;
	private T record;
	private List<T> records;
	private int totalRecordCount;
	private String message;
	
	public ResponseJSON(String result, List<T> records, int totalRecordCount) {
		this.result = result;
		this.records = records;
		this.totalRecordCount = totalRecordCount;
	}
	
	public ResponseJSON(String result, T record) {
		this.result = result;
		this.record = record;
	}
	
	public ResponseJSON(String result, String message) {
		this.result = result;
		this.message = message;
	}

	@JSON(name="Result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@JSON(name="Record")
	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}

	@JSON(name="Records")
	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	@JSON(name="TotalRecordCount")
	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	@JSON(name="Message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
