package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.CategoryJSON;
import com.ideais.stock.json.ItemJSON;
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.json.internal.InternalProductJSON;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.ItemService;
import com.ideais.stock.service.ProductService;
import com.ideais.stock.service.SubcategoryService;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class ProductAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String id;
	private String deleted;
	private String confirmation;
	private String query;
	private Boolean status;
	private Long categoryId;
	private Long subcategoryId;
	private int jtStartIndex;
	private int jtPageSize;
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubcategoryService subcategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ItemService itemService;

	private InternalProductJSON savedProduct;
	private Product product = new Product();
	private Dimensions dimensions = new Dimensions();

	private List<Category> categories = new ArrayList<Category>();
	private List<Product> products = new ArrayList<Product>();
	private List<Item> items = new ArrayList<Item>();

	private List<CategoryJSON> categoryJSONs = new ArrayList<CategoryJSON>();
	private List<SubcategoryJSON> subcategories = new ArrayList<SubcategoryJSON>();
	private List<InternalProductJSON> productJSONs = new ArrayList<InternalProductJSON>();
	private List<ItemJSON> itemJSONs = new ArrayList<ItemJSON>();

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
		Subcategory subcategory = subcategoryService.findById(subcategoryId);
		
		if (Validade.isValid(id)) {
			Product productToBeEdited = productService.findById(Long.valueOf(id));
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
			
			savedProduct = new InternalProductJSON(productService.save(productToBeEdited));

			return SUCCESS;

		}
		
		product.setDimensions(dimensions);
		product.setCategory(subcategory.getCategory());
		product.setSubcategory(subcategory);
		
		savedProduct = new InternalProductJSON(productService.save(product));

		return SUCCESS;
	}

	@SkipValidation
	public String deleteProduct() {
		if (Validade.isValid(id)) {
			product = productService.findById(Long.valueOf(id));

			if (!"ok".equals(confirmation)) {
				items = itemService.findByProductId(product, true);
				itemJSONs = new ArrayList<ItemJSON>();

				if (items != null) {
					if (items.size() > 0) {
						for (Item item : items) {
							itemJSONs.add(new ItemJSON(item));
						}
					}
				}
				return SUCCESS;
			}

			productService.delete(product);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	@SkipValidation
	public String getProductById() {
		if(Validade.isValid(id)) {
			product = productService.findById(Long.valueOf(id));
		}

		return SUCCESS;
	}

	@SkipValidation
	public String listProducts() {
		products = productService.personalizedQuery("id","asc","true","0","10", false);
		return SUCCESS;
	}
	
	@SkipValidation
	public String getPaginatedProducts() {
		productJSONs.clear();
		
		if (query == null || "".equals(query)) {
			products = productService.personalizedQuery("id","asc",String.valueOf(status),String.valueOf(jtStartIndex),String.valueOf(jtPageSize), false);
		} else {
			products = productService.search("name","asc",status,jtStartIndex, jtPageSize, query);
		}
		
		for (Product product : products) {
			productJSONs.add(new InternalProductJSON(product));
		}
		
		return SUCCESS;
	}

	@SkipValidation
	public String getCategoryList() {
		categoryJSONs.clear();
		for (Category category : categoryService.findAll()) {
			categoryJSONs.add(new CategoryJSON(category));
		}
		return SUCCESS;
	}
	
	@SkipValidation
	public String getSubcategoryList() {
		try {
			Category category = categoryService.findById(Long.valueOf(id));

			for (Subcategory subcategory : subcategoryService.findByCategoryId(
					category, true)) {
				subcategories.add(new SubcategoryJSON(subcategory));
			}
		} catch (Exception e) {
			LOG.error("Erro ao obter lista de subcategorias para pagina de cadastro de produto.", e);
			return ERROR;
		}

		return SUCCESS;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
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

	public List<Category> getCategories() {
		categories = categoryService.findAll();
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<SubcategoryJSON> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubcategoryJSON> subcategories) {
		this.subcategories = subcategories;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public List<InternalProductJSON> getProductJSONs() {
		return productJSONs;
	}

	public void setProductJSONs(List<InternalProductJSON> productJSONs) {
		this.productJSONs = productJSONs;
	}

	public List<ItemJSON> getItemJSONs() {
		return itemJSONs;
	}

	public void setItemJSONs(List<ItemJSON> itemJSONs) {
		this.itemJSONs = itemJSONs;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
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

	public List<CategoryJSON> getCategoryJSONs() {
		return categoryJSONs;
	}

	public void setCategoryJSONs(List<CategoryJSON> categoryJSONs) {
		this.categoryJSONs = categoryJSONs;
	}

	public InternalProductJSON getSavedProduct() {
		return savedProduct;
	}

	public void setSavedProduct(InternalProductJSON savedProduct) {
		this.savedProduct = savedProduct;
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
	
}