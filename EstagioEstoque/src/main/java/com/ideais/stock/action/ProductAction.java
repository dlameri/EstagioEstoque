package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Pagination;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.ItemJSON;
import com.ideais.stock.json.internal.InternalCategoryJSON;
import com.ideais.stock.json.internal.InternalProductJSON;
import com.ideais.stock.json.internal.InternalSubcategoryJSON;
import com.ideais.stock.json.internal.ResponseJSON;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class ProductAction extends AbstractAction<Product, InternalProductJSON> {

	private static final long serialVersionUID = 1L;

	private Product product = new Product();
	private Dimensions dimensions = new Dimensions();

	private List<InternalCategoryJSON> categoriesJSON = new ArrayList<InternalCategoryJSON>();
	private List<InternalSubcategoryJSON> subcategoriesJSON = new ArrayList<InternalSubcategoryJSON>();
	
	private List<ItemJSON> itemsJSON = new ArrayList<ItemJSON>();

	@Validations(
		requiredStrings={
			@RequiredStringValidator(fieldName = "product.name", type = ValidatorType.FIELD, message = "Nome não pode ser nulo."),
			@RequiredStringValidator(fieldName = "product.longDescription", type = ValidatorType.FIELD, message = "Descrição longa não pode ser nulo."),
			@RequiredStringValidator(fieldName = "product.shortDescription", type = ValidatorType.FIELD, message = "Descrição curta não pode ser nulo."),
			@RequiredStringValidator(fieldName = "product.brand", type = ValidatorType.FIELD, message = "Marca não pode ser nulo."),
			@RequiredStringValidator(fieldName = "product.model", type = ValidatorType.FIELD, message = "Modelo não pode ser nulo."),

		}, 
		stringLengthFields={ 
			@StringLengthFieldValidator(fieldName = "product.name", type = ValidatorType.FIELD, minLength = "3", maxLength = "45", message = "Nome muito curto.") 
		},
		requiredFields={
			@RequiredFieldValidator(fieldName = "product.weight", type = ValidatorType.FIELD, message = "Peso não pode ser nulo."),
			@RequiredFieldValidator(fieldName = "product.warranty", type = ValidatorType.FIELD, message = "Garantia não pode ser nulo."),
			@RequiredFieldValidator(fieldName = "dimensions.height", type = ValidatorType.FIELD, message = "Altura não pode ser nulo."),
			@RequiredFieldValidator(fieldName = "dimensions.width", type = ValidatorType.FIELD, message = "Largura não pode ser nulo."),
			@RequiredFieldValidator(fieldName = "dimensions.depth", type = ValidatorType.FIELD, message = "Profundidade não pode ser nulo."),
			@RequiredFieldValidator(fieldName = "subcategoryId", type = ValidatorType.FIELD, message = "Selecione uma subcategoria.") 
		}, 
		fieldExpressions={ 
			@FieldExpressionValidator(fieldName = "subcategoryId", expression = "subcategoryId > 0", message = "Selecione uma subcategoria.") 
		},
		conversionErrorFields={
			@ConversionErrorFieldValidator(fieldName = "product.weight", message = "Deve ser um número", shortCircuit = true),
			@ConversionErrorFieldValidator(fieldName = "product.warranty", message = "Deve ser um número", shortCircuit = true),
			@ConversionErrorFieldValidator(fieldName = "dimensions.height", message = "Deve ser um número", shortCircuit = true),
			@ConversionErrorFieldValidator(fieldName = "dimensions.width", message = "Deve ser um número", shortCircuit = true),
			@ConversionErrorFieldValidator(fieldName = "dimensions.depth", message = "Deve ser um número", shortCircuit = true)
		}
	)
	public String saveProduct() {
		Product savedProduct = null;
		Subcategory subcategory = subcategoryService.findById(subcategoryId);
		
		if (Validade.isValid(id)) {
			Product productToBeEdited = productService.findById(Long.valueOf(id));
			if (productToBeEdited != null) {
				productToBeEdited.setDimensions(dimensions);
				productToBeEdited.setSubcategory(subcategory);
				productToBeEdited.setCategory(subcategory.getCategory());
				productToBeEdited.setName(product.getName());
				productToBeEdited.setBrand(product.getBrand());
				productToBeEdited.setModel(product.getModel());
				productToBeEdited.setWarranty(product.getWarranty());
				productToBeEdited.setWeight(product.getWeight());
				productToBeEdited.setLongDescription(product.getLongDescription());
				productToBeEdited.setShortDescription(product.getShortDescription());
				
				savedProduct = productService.save(productToBeEdited);
			}
		} else {
			product.setDimensions(dimensions);
			product.setCategory(subcategory.getCategory());
			product.setSubcategory(subcategory);
			savedProduct = productService.save(product);
		}
			
		if (savedProduct == null) {
			responseOutput = new ResponseJSON<InternalProductJSON>("ERROR", "Erro ao salvar categoria.");
			return ERROR;
		}
		
		responseOutput = new ResponseJSON<InternalProductJSON>("OK", new InternalProductJSON(savedProduct));
		return SUCCESS;
	}

	@SkipValidation
	public String checkProductBeforeDeleting() {
		if (!Validade.isValid(id)) {
			responseOutput = new ResponseJSON<InternalProductJSON>("ERROR", "Id inválido.");
			return ERROR;
		}
	
		product = productService.findById(Long.valueOf(id));
		List<Item> items = itemService.findByProductId(product, true);
		
		if (items == null) {
			return SUCCESS;
		}
		
		for (Item item : items) {
			itemsJSON.add(new ItemJSON(item));
		}

		return SUCCESS;
	}

	@SkipValidation
	public String deleteProduct() {
		product = productService.findById(Long.valueOf(id));
		Product deletedProduct = productService.delete(product);
		
		if (deletedProduct == null) {
			responseOutput = new ResponseJSON<InternalProductJSON>("ERROR", "Erro ao deletar o produto.");
			return ERROR;
		}

		responseOutput = new ResponseJSON<InternalProductJSON>("OK", new InternalProductJSON(deletedProduct));
		return SUCCESS;
	}
	
	@SkipValidation
	public String listProducts() {
		return SUCCESS;
	}
	
	@SkipValidation
	public String getPaginatedProducts() {
		List<InternalProductJSON> productsJSON = new ArrayList<InternalProductJSON>();
		List<Product> products;
		Integer totalRecordCount;
		String[] orderSettings = jtSorting.split(" ");

		Pagination pagination = new Pagination(orderSettings[0].toLowerCase(),
				orderSettings[1].toLowerCase(), jtStartIndex, jtPageSize);
		if (StringUtils.isBlank(query)) {
			products = productService.findAllWithPagination(status, pagination, false);
			totalRecordCount = productService.getCount(status);
		} else {
			products = productService.search(status, pagination, query);
			totalRecordCount = productService.getCount(status, query);
		}
		
		for (Product product : products) {
			productsJSON.add(new InternalProductJSON(product));
		}
		
		responseOutput = new ResponseJSON<InternalProductJSON>("OK", productsJSON, totalRecordCount);
		return SUCCESS;
	}

	@SkipValidation
	public String getCategoryList() {
		categoriesJSON.clear();
		for (Category category : categoryService.findAll()) {
			categoriesJSON.add(new InternalCategoryJSON(category));
		}
		return SUCCESS;
	}
	
	@SkipValidation
	public String getSubcategoryList() {
		try {
			Category category = categoryService.findById(Long.valueOf(id));

			for (Subcategory subcategory : subcategoryService.findByCategoryId(
					category, true)) {
				subcategoriesJSON.add(new InternalSubcategoryJSON(subcategory));
			}
		} catch (Exception e) {
			LOG.error("Erro ao obter lista de subcategorias para pagina de cadastro de produto.", e);
			return ERROR;
		}

		return SUCCESS;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public List<InternalCategoryJSON> getCategoriesJSON() {
		return categoriesJSON;
	}

	public void setCategoriesJSON(List<InternalCategoryJSON> categoriesJSON) {
		this.categoriesJSON = categoriesJSON;
	}

	public List<InternalSubcategoryJSON> getSubcategoriesJSON() {
		return subcategoriesJSON;
	}

	public void setSubcategoriesJSON(List<InternalSubcategoryJSON> subcategoriesJSON) {
		this.subcategoriesJSON = subcategoriesJSON;
	}

	public List<ItemJSON> getItemsJSON() {
		return itemsJSON;
	}

	public void setItemsJSON(List<ItemJSON> itemsJSON) {
		this.itemsJSON = itemsJSON;
	}
	
}