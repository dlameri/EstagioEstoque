package com.ideais.stock.util;

import java.util.List;

public class ResponseJSON<T> {

	private String result;
	private List<T> records;
	private int totalRecordCount;
	
	public ResponseJSON(String result, List<T> records, int totalRecordCount) {
		this.result = result;
		this.records = records;
		this.totalRecordCount = totalRecordCount;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	
}
