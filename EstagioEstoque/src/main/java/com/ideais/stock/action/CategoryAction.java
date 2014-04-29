package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.json.internal.InternalCategoryJSON;
import com.ideais.stock.json.internal.ResponseJSON;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.ItemService;
import com.ideais.stock.service.ProductService;
import com.ideais.stock.service.SubcategoryService;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class CategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubcategoryService subcategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ItemService itemService;
	
	private String id;
	private String confirmation;
	private String query;
	private Boolean status;
	private Long categoryId;
	private int jtStartIndex;
	private int jtPageSize;

	private Category category = new Category();
	
	private ResponseJSON<InternalCategoryJSON> responseOutput;
	private ResponseJSON<InternalCategoryJSON> inputResponseError = new ResponseJSON<InternalCategoryJSON>("ERROR", "Por favor, verifique os campos.");
	private List<SubcategoryJSON> subcategoryJSONs = new ArrayList<SubcategoryJSON>();


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
	public String requestDeleteCategory() {
		category = categoryService.findById(Long.valueOf(id));

		List<Subcategory> subcategories = subcategoryService.findByCategoryId(category, true);

		if( subcategories == null ) {
			return "SUCCESS";
		}
			
		for (Subcategory subcategory : subcategories) {
			subcategoryJSONs.add(new SubcategoryJSON(subcategory));
		}

		return SUCCESS;
	}
		
	@SkipValidation
	public String deleteCategory() {
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
		
		
		if (StringUtils.isBlank(query)) {
			categories = categoryService.personalizedQuery("id","asc", status, jtStartIndex, jtPageSize);
		} else {
			categories = categoryService.search("name","asc", status, jtStartIndex, jtPageSize, query);
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
		return subcategoryJSONs;
	}

	public void setSubcategoryJSONs(List<SubcategoryJSON> subcategoryJSONs) {
		this.subcategoryJSONs = subcategoryJSONs;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
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

	public int getJtStartIndex() {
		return jtStartIndex;
	}

	public void setJtStartIndex(int jtStartIndex) {
		this.jtStartIndex = jtStartIndex;
	}

	public int getJtPageSize() {
		return jtPageSize;
	}

	public void setJtPageSize(int jtPageSize) {
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
	
}
