package com.ideais.stock.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.json.internal.ResponseJSON;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.ItemService;
import com.ideais.stock.service.ProductService;
import com.ideais.stock.service.SubcategoryService;
import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractAction<T, K> extends ActionSupport {

	protected static final long serialVersionUID = 1L;
	
	@Autowired
	protected CategoryService categoryService;
	@Autowired
	protected SubcategoryService subcategoryService;
	@Autowired
	protected ProductService productService;
	@Autowired
	protected ItemService itemService;

	protected String id;
	protected String query;
	protected Boolean status;
	protected Long categoryId;
	protected Long subcategoryId;
	protected Long productId;
	protected String jtStartIndex;
	protected String jtPageSize;
	protected String jtSorting;
	
	protected ResponseJSON<K> responseOutput;
	protected ResponseJSON<K> inputResponseError = new ResponseJSON<K>(
			"ERROR", "Por favor, verifique os campos.");

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
	public String getJtStartIndex() {
		return jtStartIndex;
	}
	public void setJtStartIndex(String jtStartIndex) {
		this.jtStartIndex = jtStartIndex;
	}
	public String getJtPageSize() {
		return jtPageSize;
	}
	public void setJtPageSize(String jtPageSize) {
		this.jtPageSize = jtPageSize;
	}
	public String getJtSorting() {
		return jtSorting;
	}
	public void setJtSorting(String jtSorting) {
		this.jtSorting = jtSorting;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public ResponseJSON<K> getResponseOutput() {
		return responseOutput;
	}
	public void setResponseOutput(ResponseJSON<K> responseOutput) {
		this.responseOutput = responseOutput;
	}
	public ResponseJSON<K> getInputResponseError() {
		return inputResponseError;
	}
	public void setInputResponseError(ResponseJSON<K> inputResponseError) {
		this.inputResponseError = inputResponseError;
	}

}
