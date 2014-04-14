package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.SubcategoryService;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class SubcategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String id;

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubcategoryService subcategoryService;
	
	private Category category = new Category();
	private Subcategory subcategory = new Subcategory();
	
	private List<Subcategory> subcategories = new ArrayList<Subcategory>();
	private List<Category> categories = new ArrayList<Category>();

	

	@Validations(
	    requiredStrings={
	    	@RequiredStringValidator(fieldName="subcategory.name", type= ValidatorType.FIELD, message="Nome não pode ser nulo.")
	    },
	    stringLengthFields={
	    	@StringLengthFieldValidator(fieldName="subcategory.name", type= ValidatorType.FIELD, minLength="3", maxLength="45", message="Nome muito curto.")
	    },
	    requiredFields={ 
	    		@RequiredFieldValidator(fieldName="category.id", type= ValidatorType.FIELD, message="Selecione uma categoria.")
	    }
	)
	public String addSubcategory() {
		category = categoryService.findById(category.getId());
		subcategory.setCategory(category);
		subcategoryService.save(subcategory);
		return SUCCESS;
	}

	public List<Subcategory> getSubcategories() {
		subcategories = subcategoryService.findAll();
		return subcategories;
	}
	
	public List<Category> getCategories() {
		categories = categoryService.findAll();
		return categories;
	}

	@SkipValidation
	public String listSubcategories() {
		return SUCCESS;
	}
	
	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setCategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}
	
	@SkipValidation
	public String deleteSubcategory() {
		try {
			if (Validade.isValid(id)) {
				subcategoryService.delete(subcategoryService.findById(Long.valueOf(id)));
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
