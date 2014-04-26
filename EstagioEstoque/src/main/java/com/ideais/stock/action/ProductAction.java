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
import com.ideais.stock.json.ItemJSON;
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.ItemService;
import com.ideais.stock.service.ProductService;
import com.ideais.stock.service.SubcategoryService;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.ActionSupport;
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

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubcategoryService subcategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ItemService itemService;

	private Subcategory subcategory = new Subcategory();
	private Product product = new Product();
	private Dimensions dimensions = new Dimensions();

	private List<Category> categories = new ArrayList<Category>();
	private List<Product> products = new ArrayList<Product>();
	private List<Item> items = new ArrayList<Item>();

	private List<SubcategoryJSON> subcategories = new ArrayList<SubcategoryJSON>();
	private List<ItemJSON> itemJSONs = new ArrayList<ItemJSON>();

	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "product.name", type = ValidatorType.FIELD, message = "Nome não pode ser nulo."),
			@RequiredStringValidator(fieldName = "product.longDescription", type = ValidatorType.FIELD, message = "Descrição longa não pode ser nulo."),
			@RequiredStringValidator(fieldName = "product.shortDescription", type = ValidatorType.FIELD, message = "Descrição curta não pode ser nulo."),
			@RequiredStringValidator(fieldName = "product.brand", type = ValidatorType.FIELD, message = "Marca não pode ser nulo."),
			@RequiredStringValidator(fieldName = "product.model", type = ValidatorType.FIELD, message = "Modelo não pode ser nulo."),

	}, stringLengthFields = { @StringLengthFieldValidator(fieldName = "product.name", type = ValidatorType.FIELD, minLength = "3", maxLength = "45", message = "Nome muito curto.") }, requiredFields = {
			@RequiredFieldValidator(fieldName = "product.weight", type = ValidatorType.FIELD, message = "Peso não pode ser nulo."),
			@RequiredFieldValidator(fieldName = "product.warranty", type = ValidatorType.FIELD, message = "Garantia não pode ser nulo."),
			@RequiredFieldValidator(fieldName = "dimensions.height", type = ValidatorType.FIELD, message = "Altura não pode ser nulo."),
			@RequiredFieldValidator(fieldName = "dimensions.width", type = ValidatorType.FIELD, message = "Largura não pode ser nulo."),
			@RequiredFieldValidator(fieldName = "dimensions.depth", type = ValidatorType.FIELD, message = "Profundidade não pode ser nulo."),
			@RequiredFieldValidator(fieldName = "subcategory.id", type = ValidatorType.FIELD, message = "Selecione uma subcategoria.") }, fieldExpressions = { @FieldExpressionValidator(fieldName = "subcategory.id", expression = "subcategory.id > 0", message = "Selecione uma subcategoria.") })
	public String addProduct() {
		subcategory = subcategoryService.findById(subcategory.getId());
		product.setDimensions(dimensions);
		product.setCategory(subcategory.getCategory());
		product.setSubcategory(subcategory);
		productService.save(product);

		return SUCCESS;
	}

	@SkipValidation
	public String deleteCategory() {
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
		if (Validade.isValid(id)) {
			product = productService.findById(Long.valueOf(id));
			return SUCCESS;
		}
		return ERROR;
	}

	@SkipValidation
	public String listProducts() {
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

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
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
		products = productService.findAll(true);
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
}