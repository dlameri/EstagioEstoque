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
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.json.internal.CategoryInternalJSON;
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

	private String id;
	private String deleted;
	private String confirmation;
	private String query;
	private Boolean status;
	private Long categoryId;
	private int jtStartIndex;
	private int jtPageSize;

	private Category category = new Category();

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubcategoryService subcategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ItemService itemService;

	private CategoryInternalJSON savedCategory;
	private List<Category> categories;
	private List<Subcategory> subcategories;
	private List<Product> products;
	private List<Item> items;
	private List<CategoryInternalJSON> categoryJSONList = new ArrayList<CategoryInternalJSON>();
	private List<SubcategoryJSON> subcategoryJSONs = new ArrayList<SubcategoryJSON>();
	private List<ProductJSON> productJSONs = new ArrayList<ProductJSON>();
	private List<ItemJSON> itemJSONs = new ArrayList<ItemJSON>();

	@Validations(
		requiredStrings={ 
			@RequiredStringValidator(fieldName = "category.name", type = ValidatorType.FIELD, message = "Category required") 
		}, 
		stringLengthFields={ 
			@StringLengthFieldValidator(fieldName = "category.name", type = ValidatorType.FIELD, minLength = "3", maxLength = "45", message = "Nome muito curto.") 
		}
	)
	public String saveCategory() {
		try {
			savedCategory = new CategoryInternalJSON(categoryService.save(category));
			return SUCCESS;

		} catch (Exception e) {
			return ERROR;
		}
	}

	@SkipValidation
	public String deleteCategory() {
		SubcategoryJSON subcategoryJSON;
		ProductJSON productJSON;
		
		category = categoryService.findById(Long.valueOf(id));

		if (!"ok".equals(confirmation)) {
			subcategories = subcategoryService.findByCategoryId(category, true);

			if (subcategories != null) {
				if (subcategories.size() > 0) {
					for (Subcategory subcategory : subcategories) {
						subcategoryJSON = new SubcategoryJSON(subcategory);
						products = productService.findBySubcategoryId(subcategory, true);
						productJSONs = new ArrayList<ProductJSON>();
						
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
							}
						}
						subcategoryJSON.setProducts(productJSONs);
						subcategoryJSONs.add(subcategoryJSON);
					}

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
	
	@SkipValidation
	public String getPaginatedCategories() {
		categoryJSONList.clear();
		
		if (query == null || "".equals(query)) {
			categories = categoryService.personalizedQuery("id","asc", status, jtStartIndex, jtPageSize);
		} else {
			categories = categoryService.search("name","asc", status, jtStartIndex, jtPageSize, query);
		}
		
		for (Category category : categories) {
			categoryJSONList.add(new CategoryInternalJSON(category));
		}
		
		return SUCCESS;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
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

	public List<CategoryInternalJSON> getCategoryJSONList() {
		return categoryJSONList;
	}

	public void setCategoryJSONList(List<CategoryInternalJSON> categoryJSONList) {
		this.categoryJSONList = categoryJSONList;
	}

	public CategoryInternalJSON getSavedCategory() {
		return savedCategory;
	}

	public void setSavedCategory(CategoryInternalJSON savedCategory) {
		this.savedCategory = savedCategory;
	}
	
}
