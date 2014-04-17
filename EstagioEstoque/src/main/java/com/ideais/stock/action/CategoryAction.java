package com.ideais.stock.action;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;


public class CategoryAction extends ActionSupport {

	private static final String NOT_DELETABLE = "notDeletable";

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private Category category = new Category();
	
	@Autowired
	private CategoryService categoryService;
	private List<Category> categories;
	private List<Subcategory> subcategories;
	
	@Validations(
	    requiredStrings={
	    	@RequiredStringValidator(fieldName="category.name", type= ValidatorType.FIELD, message="Category required")
	    },
	    stringLengthFields={
	    	@StringLengthFieldValidator(fieldName="category.name", type= ValidatorType.FIELD, minLength="3", maxLength="45", message="Nome muito curto.")
	    }
	)
	public String saveCategory() {
		try {
			if (category.getId() != null) {
				categoryService.save(category);
				return SUCCESS;
			}
			categoryService.save(category);
			return SUCCESS;
			
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	@SkipValidation
	public String deleteCategory() {
		category = categoryService.findById(Long.valueOf(id));
		subcategories = category.getSubcategories();
		
		if (subcategories != null) {
			return NOT_DELETABLE;
		}
		
		categoryService.delete(category);
		return SUCCESS;
	}

	@SkipValidation
	public String listCategories() {
		if (Validade.isValid(id)) {
			category = categoryService.findById(Long.valueOf(id));
			return SUCCESS;
		}
		categories = categoryService.findAll();
		return SUCCESS;
	}

	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public List<Category> getCategories() {
		categories = categoryService.findAll();
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
