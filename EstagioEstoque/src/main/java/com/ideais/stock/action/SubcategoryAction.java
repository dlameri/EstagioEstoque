package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.ProductJSON;
import com.ideais.stock.json.internal.InternalSubcategoryJSON;
import com.ideais.stock.json.internal.ResponseJSON;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class SubcategoryAction extends AbstractAction<Subcategory, InternalSubcategoryJSON>{

	private static final long serialVersionUID = 1L;

	private Subcategory subcategory = new Subcategory();

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
		Subcategory savedSubcategory;

		if (!Validade.isValid(id)) {
			Category category = categoryService.findById(categoryId);
			subcategory.setCategory(category);
		} else {
			subcategory.setId(Long.valueOf(id));
		}
		
		savedSubcategory = subcategoryService.save(subcategory);
		responseOutput = new ResponseJSON<InternalSubcategoryJSON>("OK", new InternalSubcategoryJSON(savedSubcategory));

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
		subcategory = subcategoryService.findById(Long.valueOf(id));
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

		responseOutput = new ResponseJSON<InternalSubcategoryJSON>( "OK", subcategoriesJSON, 10 );
		return SUCCESS;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setCategory(Subcategory subcategory) {
		this.subcategory = subcategory;
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

}
