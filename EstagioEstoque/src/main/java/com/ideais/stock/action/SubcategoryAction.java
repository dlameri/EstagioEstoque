package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.ItemJSON;
import com.ideais.stock.json.ProductJSON;
import com.ideais.stock.json.internal.SubcategoryInternalJSON;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.ItemService;
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

	private String id;
	private String deleted;
	private String confirmation;
	private Boolean status;
	private Long categoryId;
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

	private Category category = new Category();
	private Subcategory subcategory = new Subcategory();
	private SubcategoryInternalJSON savedSubcategory;

	private List<Subcategory> subcategories = new ArrayList<Subcategory>();
	private List<Category> categories = new ArrayList<Category>();
	private List<Product> products = new ArrayList<Product>();
	private List<Item> items;

	private List<ProductJSON> productJSONs = new ArrayList<ProductJSON>();
	private List<ItemJSON> itemJSONs = new ArrayList<ItemJSON>();
	
	private List<SubcategoryInternalJSON> subcategoryJSONList = new ArrayList<SubcategoryInternalJSON>(); 

	@Validations(
		requiredStrings={ 
			@RequiredStringValidator(fieldName = "subcategory.name", type = ValidatorType.FIELD, message = "Nome não pode ser nulo.") }, stringLengthFields = { @StringLengthFieldValidator(fieldName = "subcategory.name", type = ValidatorType.FIELD, minLength = "3", maxLength = "45", message = "Nome muito curto.") 
		}, 
		requiredFields={ 
			@RequiredFieldValidator(fieldName = "categoryId", type = ValidatorType.FIELD, message = "Selecione uma categoria.") 
		}
	)
	public String saveSubcategory() {
		category = categoryService.findById(categoryId);
		subcategory.setCategory(category);
		
		savedSubcategory = new SubcategoryInternalJSON(subcategoryService.save(subcategory));
		return SUCCESS;
	}

	@SkipValidation
	public String deleteSubcategory() {
		if (Validade.isValid(id)) {
			ProductJSON productJSON;

			subcategory = subcategoryService.findById(Long.valueOf(id));

			if (!"ok".equals(confirmation)) {
				products = productService.findBySubcategoryId(subcategory, true);
				if (products != null) {
					if (products.size() > 0) {
						for (Product product : products) {
							productJSON = new ProductJSON(product);
							items = itemService.findByProductId(product, true);
							itemJSONs = new ArrayList<ItemJSON>();

							if (items != null) {
								if (items.size() > 0) {
									for (Item item : items) {
										itemJSONs.add(new ItemJSON(item));
									}
								}
							}
							productJSON.setItems(itemJSONs);
							productJSONs.add(productJSON);
						}
						return SUCCESS;
					}
				}
			}

				subcategoryService.delete(subcategory);
				return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@SkipValidation
	public String listSubcategories() {
		if (Validade.isValid(id)) {
			subcategory = subcategoryService.findById(Long.valueOf(id));
			return SUCCESS;
		}
		subcategories = subcategoryService.findAll(true);
		return SUCCESS;
	}
	
	@SkipValidation
	public String getSubcategoriesByCategoryId() {
		subcategoryJSONList.clear();
		
		Category category = new Category();
		category.setId(categoryId);
		subcategories = subcategoryService.findByCategoryId(category, true);
		
		for (Subcategory subcategory : subcategories) {
			subcategoryJSONList.add(new SubcategoryInternalJSON(subcategory));
		}
		
		return SUCCESS;
	}

	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	public List<Category> getCategories() {
		categories = categoryService.findAll();
		return categories;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getId() {
		return id;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public List<ProductJSON> getProductJSONs() {
		return productJSONs;
	}

	public void setProductJSONs(List<ProductJSON> productJSONs) {
		this.productJSONs = productJSONs;
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

	public SubcategoryInternalJSON getSavedSubcategory() {
		return savedSubcategory;
	}

	public void setSavedSubcategory(SubcategoryInternalJSON savedSubcategory) {
		this.savedSubcategory = savedSubcategory;
	}

	public List<SubcategoryInternalJSON> getSubcategoryJSONList() {
		return subcategoryJSONList;
	}

	public void setSubcategoryJSONList(
			List<SubcategoryInternalJSON> subcategoryJSONList) {
		this.subcategoryJSONList = subcategoryJSONList;
	}

}
