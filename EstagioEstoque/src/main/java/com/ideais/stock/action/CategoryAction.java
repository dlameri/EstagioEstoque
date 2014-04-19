package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class CategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String id;
	private String deleted;
	private String confirmation;

	private Category category = new Category();

	@Autowired
	private CategoryService categoryService;
	private List<Category> categories;
	private List<Subcategory> subcategories;
	private List<SubcategoryJSON> subcategoryJSONs = new ArrayList<SubcategoryJSON>();

	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "category.name", type = ValidatorType.FIELD, message = "Category required") }, stringLengthFields = { @StringLengthFieldValidator(fieldName = "category.name", type = ValidatorType.FIELD, minLength = "3", maxLength = "45", message = "Nome muito curto.") 
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
		
		if (!"ok".equals(confirmation)) {
			subcategories = category.getSubcategories();
		
			if (subcategories != null) {
				if (subcategories.size() > 0) {
	//				for (Subcategory subcategory : subcategories) {
	//					subcategoryJSONs.add(new SubcategoryJSON(subcategory));
	//				}
	
					return SUCCESS;
				}
			}
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

	public List<SubcategoryJSON> getSubcategoryJSONs() {
		return subcategoryJSONs;
	}

	public void setSubcategoryJSONs(List<SubcategoryJSON> subcategoryJSONs) {
		this.subcategoryJSONs = subcategoryJSONs;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	
}
