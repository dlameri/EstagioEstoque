package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.ProductJSON;
import com.ideais.stock.json.internal.InternalSubcategoryJSON;
import com.ideais.stock.json.internal.ResponseJSON;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.ProductService;
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

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubcategoryService subcategoryService;
	@Autowired
	private ProductService productService;

	private String id;
	private Boolean status;
	private Long categoryId;
	private int jtStartIndex;
	private int jtPageSize;

	private Subcategory subcategory = new Subcategory();

	private ResponseJSON<InternalSubcategoryJSON> responseOutput;
	private ResponseJSON<InternalSubcategoryJSON> inputResponseError = new ResponseJSON<InternalSubcategoryJSON>("ERROR", "Por favor, verifique os campos.");
	private List<ProductJSON> productsJSON = new ArrayList<ProductJSON>();
	
	@Validations(
		requiredStrings={ 
			@RequiredStringValidator(fieldName = "subcategory.name", type = ValidatorType.FIELD, message = "Nome não pode ser nulo.") }, stringLengthFields = { @StringLengthFieldValidator(fieldName = "subcategory.name", type = ValidatorType.FIELD, minLength = "3", maxLength = "45", message = "Nome muito curto.") 
		}, 
		requiredFields={ 
			@RequiredFieldValidator(fieldName = "categoryId", type = ValidatorType.FIELD, message = "Selecione uma categoria.") 
		}
	)
	public String saveSubcategory() {
		InternalSubcategoryJSON savedSubcategory;

		Category category = categoryService.findById(categoryId);
		subcategory.setCategory(category);
		
		savedSubcategory = new InternalSubcategoryJSON(subcategoryService.save(subcategory));
		
		responseOutput = new ResponseJSON<InternalSubcategoryJSON>("OK", savedSubcategory);
		return SUCCESS;
	}

	@SkipValidation
	public String checkSubcategoryBeforeDeleting() {
		if (Validade.isValid(id)) {
			responseOutput = new ResponseJSON<InternalSubcategoryJSON>("ERROR", "Id inválido.");
			return ERROR;
		}
		
		subcategory = subcategoryService.findById(Long.valueOf(id));

		List<Product> products = productService.findBySubcategoryId(subcategory, true);
		if (products == null) {
			return SUCCESS;
		}

		for (Product product : products) {
			productsJSON.add(new ProductJSON(product));
		}

		return SUCCESS;
	}
	
	@SkipValidation
	public String deleteSubcategory() {
		Subcategory deletedSubcategory = subcategoryService.delete(subcategory);

		if (deletedSubcategory == null) {
			responseOutput = new ResponseJSON<InternalSubcategoryJSON>("ERROR", "Erro ao deletar categoria.");
			return ERROR;
		}
			
		responseOutput = new ResponseJSON<InternalSubcategoryJSON>("OK", new InternalSubcategoryJSON(deletedSubcategory));
		return SUCCESS;
	}

	@SkipValidation
	public String listSubcategories() {
		return SUCCESS;
	}
	
	@SkipValidation
	public String getSubcategoriesByCategoryId() {
		List<InternalSubcategoryJSON> subcategoriesJSON = new ArrayList<InternalSubcategoryJSON>();
		
		Category category = new Category();
		category.setId(categoryId);
		List<Subcategory> subcategories = subcategoryService.findByCategoryId(category, true);
		
		for (Subcategory subcategory : subcategories) {
			subcategoriesJSON.add(new InternalSubcategoryJSON(subcategory));
		}
		responseOutput = new ResponseJSON<InternalSubcategoryJSON>( "OK", subcategoriesJSON, subcategoriesJSON.get(0).getCount() );
		
		return SUCCESS;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setCategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public List<ProductJSON> getProductJSONs() {
		return productsJSON;
	}

	public void setProductJSONs(List<ProductJSON> productJSONs) {
		this.productsJSON = productJSONs;
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

	public ResponseJSON<InternalSubcategoryJSON> getResponseOutput() {
		return responseOutput;
	}

	public void setResponseOutput(
			ResponseJSON<InternalSubcategoryJSON> responseOutput) {
		this.responseOutput = responseOutput;
	}

	public ResponseJSON<InternalSubcategoryJSON> getInputResponseError() {
		return inputResponseError;
	}

	public void setInputResponseError(
			ResponseJSON<InternalSubcategoryJSON> inputResponseError) {
		this.inputResponseError = inputResponseError;
	}

}
