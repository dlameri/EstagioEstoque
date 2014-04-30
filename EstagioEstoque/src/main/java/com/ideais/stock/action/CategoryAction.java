package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Pagination;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.json.internal.InternalCategoryJSON;
import com.ideais.stock.json.internal.ResponseJSON;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.SubcategoryService;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class CategoryAction extends ActionSupport {

	private static final String DEFAULT_ORDER = "asc";

	private static final String DEFAULT_ORDER_COLUMN = "id";

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubcategoryService subcategoryService;
	
	private String id;
	private String query;
	private Boolean status;
	private Long categoryId;
	private String jtStartIndex;
	private String jtPageSize;

	private Category category = new Category();
	
	private ResponseJSON<InternalCategoryJSON> responseOutput;
	private ResponseJSON<InternalCategoryJSON> inputResponseError = new ResponseJSON<InternalCategoryJSON>("ERROR", "Por favor, verifique os campos.");
	private List<SubcategoryJSON> subcategoriesJSON = new ArrayList<SubcategoryJSON>();


	@Validations(
		requiredStrings={ 
			@RequiredStringValidator(fieldName = "category.name", type = ValidatorType.FIELD, message = "Category required") 
		}, 
		stringLengthFields={ 
			@StringLengthFieldValidator(fieldName = "category.name", type = ValidatorType.FIELD, minLength = "3", maxLength = "45", message = "Nome muito curto.") 
		}
	)
	public String saveCategory() {
		Category savedCategory = null;

		if (Validade.isValid(id)) {
		Category categoryToBeEdited = categoryService.findById(Long.valueOf(id));
		
			if (categoryToBeEdited != null) {
				categoryToBeEdited.setActive(category.getActive());
				categoryToBeEdited.setName(category.getName());
	
				savedCategory = categoryService.save(categoryToBeEdited);
			}
		} else {
			savedCategory = categoryService.save(category);
		}
		
	
		if (savedCategory == null) {
			responseOutput = new ResponseJSON<InternalCategoryJSON>("ERROR", "Erro ao salvar categoria.");
			return ERROR;
		}
		
		responseOutput = new ResponseJSON<InternalCategoryJSON>("OK", new InternalCategoryJSON(savedCategory));
		return SUCCESS;
	}

	@SkipValidation
	public String checkCategoryBeforeDeleting() {
		if (!Validade.isValid(id)) {
			responseOutput = new ResponseJSON<InternalCategoryJSON>("ERROR", "Id inválido.");
			return ERROR;
		}
		
		category = categoryService.findById(Long.valueOf(id));

		List<Subcategory> subcategories = subcategoryService.findByCategoryId(category, true);

		if( subcategories == null ) {
			return SUCCESS;
		}
			
		for (Subcategory subcategory : subcategories) {
			subcategoriesJSON.add(new SubcategoryJSON(subcategory));
		}

		return SUCCESS;
	}
		
	@SkipValidation
	public String deleteCategory() {
		category = categoryService.findById(Long.valueOf(id));
		Category deletedCategory = categoryService.delete(category);
		
		if (deletedCategory == null) {
			responseOutput = new ResponseJSON<InternalCategoryJSON>("ERROR", "Erro ao deletar categoria.");
			return ERROR;
		}
		
		responseOutput = new ResponseJSON<InternalCategoryJSON>("OK", new InternalCategoryJSON(deletedCategory));
		return SUCCESS;
	}

	@SkipValidation
	public String listCategories() {
		return SUCCESS;
	}
	
	@SkipValidation
	public String getPaginatedCategories() {
		List<Category> categories;
		List<InternalCategoryJSON> categoriesJSON = new ArrayList<InternalCategoryJSON>();
		
		Pagination pagination = new Pagination(DEFAULT_ORDER_COLUMN, DEFAULT_ORDER, jtStartIndex, jtPageSize);
		
		if (StringUtils.isBlank(query)) {
			categories = categoryService.findAllWithPagination(status, pagination);
		} else {
			categories = categoryService.search(status, pagination, query);
		}
		
		for (Category category : categories) {
			categoriesJSON.add(new InternalCategoryJSON(category));
		}
		
		responseOutput = new ResponseJSON<InternalCategoryJSON>( "OK", categoriesJSON, categoryService.getCount(status) );
		
		return SUCCESS;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SubcategoryJSON> getSubcategoryJSONs() {
		return subcategoriesJSON;
	}

	public void setSubcategoryJSONs(List<SubcategoryJSON> subcategoryJSONs) {
		this.subcategoriesJSON = subcategoryJSONs;
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

	public ResponseJSON<InternalCategoryJSON> getJsonOutput() {
		return responseOutput;
	}

	public void setJsonOutput(ResponseJSON<InternalCategoryJSON> jsonOutput) {
		this.responseOutput = jsonOutput;
	}

	public ResponseJSON<InternalCategoryJSON> getResponseOutput() {
		return responseOutput;
	}

	public void setResponseOutput(ResponseJSON<InternalCategoryJSON> responseOutput) {
		this.responseOutput = responseOutput;
	}

	public ResponseJSON<InternalCategoryJSON> getInputResponseError() {
		return inputResponseError;
	}

	public void setInputResponseError(
			ResponseJSON<InternalCategoryJSON> inputResponseError) {
		this.inputResponseError = inputResponseError;
	}

	public List<SubcategoryJSON> getSubcategoriesJSON() {
		return subcategoriesJSON;
	}

	public void setSubcategoriesJSON(List<SubcategoryJSON> subcategoriesJSON) {
		this.subcategoriesJSON = subcategoriesJSON;
	}
	
}
